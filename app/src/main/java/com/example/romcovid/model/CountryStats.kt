package com.example.romcovid.model

import android.widget.ImageView

data class CountryStats(
    val flag: String?,
    val country: String?,
    val cases: String?,
    val todayCases: String?,
    val todayDeaths: String?,
    val deaths: String?,
    val recovered: String?,
    val active: String?,
    val critical: String?
) {
    constructor() : this("", "", "", "", "", "", "", "", "")
}
