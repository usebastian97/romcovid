package com.example.romcovid.model

import android.widget.ImageView

data class CountryStats(
    val flag: Int,
    val countryName: String,
    val childItemList: ArrayList<ChildItem>,
    var isExpandable: Boolean = false
)
