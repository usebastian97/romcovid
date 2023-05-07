package com.example.romcovid.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.romcovid.R
import com.example.romcovid.adapters.CountryStatsAdapter
import com.example.romcovid.databinding.FragmentAllCountryStatsBinding
import com.example.romcovid.model.ChildItem
import com.example.romcovid.model.CountryStats

class CountryStatsFragment : Fragment() {

    private var _binding: FragmentAllCountryStatsBinding? = null
    private val binding get() = _binding!!

    private lateinit var parentRecyclerView: RecyclerView
    private lateinit var parentList: ArrayList<CountryStats>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAllCountryStatsBinding.inflate(inflater, container, false)

        binding.rvCountry.layoutManager = LinearLayoutManager(context)
        binding.rvCountry.setHasFixedSize(true)
        parentList = ArrayList()

        prepareData()
        val adapter = CountryStatsAdapter(parentList)
        binding.rvCountry.adapter = adapter

        return binding.root
    }

    //TODO: add an API
    private fun prepareData() {

        val childAfghanistan = ArrayList<ChildItem>()
        childAfghanistan.add(ChildItem("23K", "1,1K", "8.8K", "19", "3,4M", "56K", "10K"))
        parentList.add(CountryStats(R.drawable.afganistan, "Afghanistan", childAfghanistan))

        val childAndorra = ArrayList<ChildItem>()
        childAndorra.add(ChildItem("23K", "1,1K", "8.8K", "19", "3,4M", "56K", "10K"))
        parentList.add(CountryStats(R.drawable.ad, "Andorra", childAndorra))

        val childAG = ArrayList<ChildItem>()
        childAG.add(ChildItem("23K", "1,1K", "8.8K", "19", "3,4M", "56K", "10K"))
        parentList.add(CountryStats(R.drawable.ag, "Antigua and Barbuda", childAG))

        val ai = ArrayList<ChildItem>()
        ai.add(ChildItem("23K", "1,1K", "8.8K", "19", "3,4M", "56K", "10K"))
        parentList.add(CountryStats(R.drawable.ai, "Anguilla", ai))

        val arm = ArrayList<ChildItem>()
        arm.add(ChildItem("23K", "1,1K", "8.8K", "19", "3,4M", "56K", "10K"))
        parentList.add(CountryStats(R.drawable.am, "Armenia", arm))

        val arg = ArrayList<ChildItem>()
        arg.add(ChildItem("23K", "1,1K", "8.8K", "19", "3,4M", "56K", "10K"))
        parentList.add(CountryStats(R.drawable.ar, "Argentina", arg))

        val at = ArrayList<ChildItem>()
        at.add(ChildItem("23K", "1,1K", "8.8K", "19", "3,4M", "56K", "10K"))
        parentList.add(CountryStats(R.drawable.at, "Austria", at))

        // TODO: Rest of countries


    }

}