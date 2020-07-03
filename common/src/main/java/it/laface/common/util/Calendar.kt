package it.laface.common.util

import java.util.Calendar
import java.util.Date
import java.util.Locale

/**
 * @return date like: "Mon, 21 May 2017"
 */
val Calendar.getCompleteDayName: String
    get() {
        val dayNameShort =
            getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault())
        return "$dayNameShort, $getFullDayName"
    }

/**
 * @return date like: "21 May 2017"
 */
val Calendar.getFullDayName: String
    get() {
        val monthNameLong =
            getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault())
        val year = get(Calendar.YEAR)
        val day = get(Calendar.DAY_OF_MONTH)
        return "$day $monthNameLong $year"
    }

val Date.getFullDayName: String
    get() = toCalendar.getFullDayName

fun Calendar.isSameMonth(date: Calendar): Boolean =
    get(Calendar.MONTH) == date.get(Calendar.MONTH) && get(Calendar.YEAR) == date.get(Calendar.YEAR)

fun Calendar.isSameDay(date: Calendar): Boolean =
    get(Calendar.DAY_OF_MONTH) == date.get(Calendar.DAY_OF_MONTH) && isSameMonth(date)

val Date.toCalendar: Calendar
    get() = Calendar.getInstance().apply {
        time = this@toCalendar
    }
