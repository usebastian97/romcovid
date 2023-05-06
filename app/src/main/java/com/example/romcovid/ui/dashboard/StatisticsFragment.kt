package com.example.romcovid.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.romcovid.R
import com.example.romcovid.databinding.FragmentStatisticsBinding
import com.example.romcovid.model.CovidStats
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import timber.log.Timber


class StatisticsFragment : Fragment() {

    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    private lateinit var database: DatabaseReference
    private lateinit var deaths: DatabaseReference
    private lateinit var tested: DatabaseReference
    private lateinit var recovered: DatabaseReference


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)

        getData()
        getRecoveredData()
        getTestedData()
        getDeathsData()

        binding.detailedStatistics.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_detailed_stats)

        }
        return binding.root

    }

    private fun getData() {

        database = FirebaseDatabase.getInstance().reference.child("covid/newCases")

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val covid = snapshot.getValue(CovidStats::class.java)

                val total = covid?.total
                val yesterday = covid?.yesterday

                binding.infectedTotalTv.text = total
                binding.infectedYesterdayTv.text = yesterday
            }

            override fun onCancelled(error: DatabaseError) {
                Timber.tag("ERROR").d("Data failed!")
            }

        })
    }

    private fun getDeathsData() {

        deaths = FirebaseDatabase.getInstance().reference.child("covid/deaths")

        deaths.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val covid = snapshot.getValue(CovidStats::class.java)

                val total = covid?.total
                val yesterday = covid?.yesterday

                binding.deadTotalTv.text = total
                binding.deadYesterdayTv.text = yesterday
            }

            override fun onCancelled(error: DatabaseError) {
                Timber.tag("ERROR").d("Data failed!")
            }

        })
    }

    private fun getTestedData() {

        tested = FirebaseDatabase.getInstance().reference.child("covid/tested")

        tested.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val covid = snapshot.getValue(CovidStats::class.java)

                val total = covid?.total
                val yesterday = covid?.yesterday

                binding.testedTotalTv.text = total
                binding.testedYesterdayTv.text = yesterday
            }

            override fun onCancelled(error: DatabaseError) {
                Timber.tag("ERROR").d("Data failed!")
            }

        })
    }

    private fun getRecoveredData() {

        recovered = FirebaseDatabase.getInstance().reference.child("covid/recovered")

        recovered.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val covid = snapshot.getValue(CovidStats::class.java)

                val total = covid?.total
                binding.recoveredTotalTv.text = total
            }

            override fun onCancelled(error: DatabaseError) {
                Timber.tag("ERROR").d("Data failed!")
            }

        })
    }

}
