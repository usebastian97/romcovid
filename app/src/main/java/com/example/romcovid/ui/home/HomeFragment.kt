package com.example.romcovid.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.romcovid.R
import com.example.romcovid.databinding.FragmentHomeBinding
import com.example.romcovid.model.CovidStats
import com.example.romcovid.viewmodel.HomeViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import io.github.muddz.styleabletoast.StyleableToast
import timber.log.Timber

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var database: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        return DataBindingUtil.inflate<FragmentHomeBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
            .apply { binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {

            tracingToggle.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    textHomeStatus.setText(R.string.home_status_work)
                    val color = R.color.colorPrimary
                    val image = R.drawable.ic_contact_tracing
                    textHomeStatus.setTextColor(ContextCompat.getColor(requireContext(), color))
                    homeMainIv.setImageResource(image)
                    StyleableToast.makeText(
                        requireContext(),
                        "All tracking functionalities are activated",
                        R.style.mytoast
                    ).show()
                } else {
                    textHomeStatus.setText(R.string.home_status_idle)
                    val colorIdle = R.color.colorButtonRed
                    val imageIdle = R.drawable.ic_contact_tracing_disabled
                    textHomeStatus.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            colorIdle
                        )
                    )
                    homeMainIv.setImageResource(imageIdle)
                    StyleableToast.makeText(
                        requireContext(),
                        "All tracking functionalities are disabled",
                        R.style.toastDisable
                    ).show()

                }
            }
        }

        with(binding) {

            share.setOnClickListener {
                val share = Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name))
                    putExtra(Intent.EXTRA_TEXT, getString(R.string.share_link))
                }
                requireActivity().startActivity(
                    Intent.createChooser(
                        share,
                        getString(R.string.app_name)
                    )
                )
            }
        }

        database = FirebaseDatabase.getInstance().reference.child("covid/last24hours")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val covid = snapshot.getValue(CovidStats::class.java)

                val new = covid?.new
                val deaths = covid?.deaths
                val tested = covid?.tested

                binding.homeStatisticsIllCount.text = new
                binding.homeStatisticsDeadCount.text = deaths
                binding.homeStatisticsTestedCount.text = tested
            }

            override fun onCancelled(error: DatabaseError) {
                Timber.tag("ERROR").d("Data failed!")
            }

        })

    }
}