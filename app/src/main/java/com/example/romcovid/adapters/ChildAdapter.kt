package com.example.romcovid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.romcovid.R
import com.example.romcovid.model.ChildItem

class ChildAdapter(private val childList: ArrayList<ChildItem>) :
    RecyclerView.Adapter<ChildAdapter.ChildViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChildAdapter.ChildViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.child_items, parent, false)
        return ChildViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChildAdapter.ChildViewHolder, position: Int) {

        holder.infectedTotal.text = childList[position].total
        holder.infectedTotalYesterday.text = childList[position].yesterday
        holder.recovered.text = childList[position].totalRecovered
        holder.totalTested.text = childList[position].totalTested
        holder.totalDeaths.text = childList[position].totalDeaths
        holder.yesterdayTested.text = childList[position].yesterdayTested
        holder.yesterdayDeaths.text = childList[position].yesterdayDeaths
    }

    override fun getItemCount(): Int {
        return childList.size
    }

    inner class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val totalDeaths: TextView = itemView.findViewById(R.id.dead_total_tv)
        val yesterdayDeaths: TextView = itemView.findViewById(R.id.dead_yesterday_tv)
        val totalTested: TextView = itemView.findViewById(R.id.tested_total_tv)
        val yesterdayTested: TextView = itemView.findViewById(R.id.tested_yesterday_tv)
        val recovered: TextView = itemView.findViewById(R.id.recovered_total_tv)
        val infectedTotal: TextView = itemView.findViewById(R.id.infected_total_tv)
        val infectedTotalYesterday: TextView = itemView.findViewById(R.id.infected_yesterday_tv)
    }
}