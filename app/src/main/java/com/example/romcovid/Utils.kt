package com.example.romcovid

object Utils {

    fun checkAdult(age: Int, result: Float): String {
        val category: String = when (age) {
            in 2..19 -> {
                adultCategory(result)
            }

            else -> {
                childCategory(result)
            }
        }
        return category
    }

    private fun adultCategory(result: Float): String {

        val category: String = when {
            result < 15 -> "Severe Thinness"
            result in 15.0..16.0 -> "Moderate Thinness"
            result in 16.0..18.5 -> "Mild Thinness"
            result in 18.5..25.0 -> "Normal"
            result in 25.0..30.0 -> "Overweight"
            result in 30.0..35.0 -> "Obese Class I"
            result in 35.0..40.0 -> "Obese Class II"
            else -> "Obese Class III"
        }
        return category
    }

    private fun childCategory(result: Float): String {
        val category: String = when {
            result < 15 -> "very severely underweight"
            result in 15.0..16.0 -> "severely underweight"
            result in 16.0..18.5 -> "underweight"
            result in 18.5..25.0 -> "normal (healthy weight)"
            result in 25.0..30.0 -> "overweight"
            result in 30.0..35.0 -> "moderately obese"
            result in 35.0..40.0 -> "severely obese"
            else -> "very severely obese"
        }
        return category
    }

    fun suggestions(result: Float): String {
        val suggestion: String = when {
            result < 18.5 -> {
                "A BMI of under 18.5 indicates that a person has insufficient weight, so they may need to put on some weight. They should ask a doctor or dietitian for advice."
            }

            result in 18.5..24.9 -> {
                "A BMI of 18.5–24.9 indicates that a person has a healthy weight for their height. By maintaining a healthy weight, they can lower their risk of developing serious health problems."
            }

            result < 25 && result >= 29.9 -> {
                "A BMI of 25–29.9 indicates that a person is slightly overweight. A doctor may advise them to lose some weight for health reasons. They should talk with a doctor or dietitian for advice."
            }

            else -> {
                "A BMI of over 30 indicates that a person has obesity. Their health may be at risk if they do not lose weight. They should talk with a doctor or dietitian for advice."
            }
        }
        return suggestion
    }
}