package it.laface.common.util

import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * @return date like: "01 January 2017"
 */
val Calendar.getFullDayName: String
    get() {
        val monthName = getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        val year = get(Calendar.YEAR)
        return "$day $monthName $year"
    }

val Calendar.day: String
    get() = when (val value = get(Calendar.DAY_OF_MONTH)) {
        in 0..9 -> "0$value"
        else -> value.toString()
    }

val Date.getFullDayName: String
    get() = toCalendar.getFullDayName

val Date.toCalendar: Calendar
    get() = Calendar.getInstance().apply {
        time = this@toCalendar
    }
