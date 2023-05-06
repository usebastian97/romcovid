package com.example.romcovid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.romcovid.databinding.FragmentHomeBinding
import com.example.romcovid.model.CovidStats
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var database:DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this)[HomeViewModel::class.java]

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        getData()
        return binding.root
    }

    private fun getData() {

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}