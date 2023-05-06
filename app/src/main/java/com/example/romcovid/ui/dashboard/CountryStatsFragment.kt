package com.example.romcovid.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.romcovid.adapters.CountryStatsAdapter
import com.example.romcovid.databinding.FragmentAllCountryStatsBinding
import com.example.romcovid.model.CountryStats
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CountryStatsFragment : Fragment() {

    private var _binding: FragmentAllCountryStatsBinding? = null
    private val binding get() = _binding!!

    private lateinit var countriesRecyclerView: RecyclerView
    private lateinit var adapter: CountryStatsAdapter
    private lateinit var database: DatabaseReference
    var countriesList = arrayListOf<CountryStats>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllCountryStatsBinding.inflate(inflater, container, false)

        binding.rvCountry.layoutManager = LinearLayoutManager(context)
        binding.rvCountry.setHasFixedSize(true)
        adapter = CountryStatsAdapter(countriesList)
        binding.rvCountry.adapter = adapter

        getCountries()

        return binding.root
    }

    private fun getCountries() {
        database = FirebaseDatabase.getInstance().getReference("countries")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (countries in snapshot.children) {
                        val countriesData = countries.getValue(CountryStats::class.java)
                        countriesList.add(countriesData!!)
                    }
                    val adapter = CountryStatsAdapter(countriesList)
                    binding.rvCountry.adapter = adapter
                    binding.rvCountry.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}