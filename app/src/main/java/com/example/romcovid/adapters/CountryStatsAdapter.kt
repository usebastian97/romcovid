package com.example.romcovid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.romcovid.R
import com.example.romcovid.model.CountryStats

class CountryStatsAdapter(private var parentCountriesList: ArrayList<CountryStats>) :
    RecyclerView.Adapter<CountryStatsAdapter.ParentViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParentViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_custom_item,
            parent, false
        )
        return ParentViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return parentCountriesList.size
    }

    override fun onBindViewHolder(holder: ParentViewHolder, position: Int) {
        val parentItem = parentCountriesList[position]

        holder.parentImageView.setImageResource(parentItem.flag)
        holder.countryName.text = parentItem.countryName
        holder.childRecyclerView.setHasFixedSize(true)
        holder.childRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)

        val adapter = ChildAdapter(parentItem.childItemList)
        holder.childRecyclerView.adapter = adapter

        val isExpandable = parentItem.isExpandable
        holder.childRecyclerView.visibility = if (isExpandable) View.VISIBLE else View.GONE

        holder.constraintLayout.setOnClickListener {

            isAnyItemExpanded(position)
            parentItem.isExpandable = !parentItem.isExpandable
            notifyItemChanged(position)
        }
    }

    private fun isAnyItemExpanded(position: Int) {
        val temp = parentCountriesList.indexOfFirst {
            it.isExpandable
        }

        if (temp >= 0 && temp != position) {
            parentCountriesList[temp].isExpandable = false
            notifyItemChanged(temp)
        }
    }

    inner class ParentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val parentImageView: ImageView = itemView.findViewById(R.id.parentLogoIv)
        val countryName: TextView = itemView.findViewById(R.id.parentTitleTv)
        val childRecyclerView: RecyclerView = itemView.findViewById(R.id.childRecycler)
        val constraintLayout: ConstraintLayout = itemView.findViewById(R.id.constraintLayout)
    }
}