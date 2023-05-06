package com.example.romcovid.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.romcovid.R
import com.example.romcovid.model.CountryStats

class CountryStatsAdapter(private val countriesList: ArrayList<CountryStats>) : RecyclerView.Adapter<CountryStatsAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_custom_item,
            parent, false
        )
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return countriesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateCountries(countriesList: List<CountryStats>) {
        this.countriesList.clear()
        this.countriesList.addAll(countriesList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentPosition = countriesList[position]

        holder.countryName.text = currentPosition.country
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val countryName: TextView = itemView.findViewById(R.id.tvCountry)
    }
}