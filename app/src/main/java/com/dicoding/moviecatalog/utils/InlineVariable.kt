package com.dicoding.moviecatalog.utils

import java.text.DateFormat
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class InlineVariable {

    fun setReleaseDate(releaseDate: String): String {
        val outputFormat: DateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date: Date = inputFormat.parse(releaseDate)

        return outputFormat.format(date)
    }

    fun setRevenue(revenue: String): String {
        val formatter: NumberFormat = DecimalFormat("#,###")
        val myNumber: Int = revenue.toInt()

        return formatter.format(myNumber)
    }

    fun delayTwoSecond() {
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}