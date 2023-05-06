package com.example.romcovid.model

data class CovidStats(
    val total: String?,
    val yesterday: String?,
    val new: String?,
    val deaths: String?,
    val tested: String?
) {
    constructor() : this("", "", "", "", "")
}
