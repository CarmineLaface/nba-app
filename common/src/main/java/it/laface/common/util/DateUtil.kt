@file:Suppress("MagicNumber", "DEPRECATION")

package it.laface.common.util

import android.annotation.SuppressLint
import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * @return date like: "01 January 2017"
 */
val Calendar.getFullDayName: String
    get() {
        val monthName = getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        return "$day $monthName $year"
    }

/**
 * @return date like: "Mon, 01 January 2017"
 */
val Calendar.getCompleteDayName: String
    @SuppressLint("DefaultLocale")
    get() {
        val locale = Locale.getDefault()
        val dayName = getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, locale)?.capitalize()
        val monthName = getDisplayName(Calendar.MONTH, Calendar.LONG, locale)
        return "$dayName, $day $monthName $year"
    }

val Calendar.day: String
    get() = when (val value = get(Calendar.DAY_OF_MONTH)) {
        in 0..9 -> "0$value"
        else -> value.toString()
    }

val Calendar.year: Int
    get() = get(Calendar.YEAR)

val Date.getFullDayName: String
    get() = toCalendar.getFullDayName

val Date.toCalendar: Calendar
    get() = Calendar.getInstance().apply {
        time = this@toCalendar
    }

infix fun Date.isSameDay(other: Date): Boolean =
    date == other.date && isSameMonth(other)

infix fun Date.isSameMonth(other: Date): Boolean =
    month == other.month && isSameYear(other)

infix fun Date.isSameYear(other: Date): Boolean =
    year == other.year
