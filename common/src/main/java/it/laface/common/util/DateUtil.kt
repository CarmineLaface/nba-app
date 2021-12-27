@file:Suppress("MagicNumber", "DEPRECATION")

package it.laface.common.util

import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.Locale.getDefault

/**
 * @return date like: "01 January 2017"
 */
val Calendar.getFullDayName: String
    get() {
        val monthName = getDisplayName(Calendar.MONTH, Calendar.LONG, getDefault())?.capitalize
        return "$dayString $monthName $year"
    }

/**
 * @return date like: "Mon, 01 January 2017"
 */
val Calendar.getCompleteDayName: String
    get() {
        val dayName = getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, getDefault())?.capitalize
        return "$dayName, $getFullDayName"
    }

val Calendar.day: Int
    get() = get(Calendar.DAY_OF_MONTH)

val Calendar.dayString: String
    get() = when (val value = day) {
        in 0..9 -> "0$value"
        else -> value.toString()
    }

val Calendar.month: Int
    get() = get(Calendar.MONTH)

val Calendar.monthString: String
    get() = when (val value = month) {
        in 0..9 -> "0$value"
        else -> value.toString()
    }

val Calendar.year: Int
    get() = get(Calendar.YEAR)

val Date.toCalendar: Calendar
    get() = Calendar.getInstance(Locale.US).apply {
        time = this@toCalendar
    }

infix fun Date.isSameDay(other: Date): Boolean =
    date == other.date && isSameMonth(other)

infix fun Date.isSameMonth(other: Date): Boolean =
    month == other.month && isSameYear(other)

infix fun Date.isSameYear(other: Date): Boolean =
    year == other.year
