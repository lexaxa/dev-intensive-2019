package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Utils
import java.lang.Math.abs
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR
const val YEAR = 365 * DAY

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

/*
0с - 1с "только что"
1с - 45с "несколько секунд назад"
45с - 75с "минуту назад"
75с - 45мин "N минут назад"
45мин - 75мин "час назад"
75мин 22ч "N часов назад"
22ч - 26ч "день назад"
26ч - 360д "N дней назад"
>360д "более года назад"
 */
fun Date.humanizeDiff(date: Date = Date()): String{
    val diff = "только что"
    val eventAfter : String
    val eventBefore : String
    val diffTime = abs(date.time - this.time)
    if(date.time - this.time < 0){
        eventAfter = "через "
        eventBefore = ""
    }else{
        eventAfter = ""
        eventBefore = " назад"
    }

    return when (diffTime) {
        in 0..2*SECOND-1 -> "только что"
        in 2*SECOND..45*SECOND -> "${eventAfter}несколько секунд$eventBefore"
        in 46*SECOND..75*SECOND -> "${eventAfter}минуту$eventBefore"
        in 76*SECOND .. 45*MINUTE -> "$eventAfter${Utils.parseTime(diffTime / MINUTE, TimeUnits.MINUTE)}$eventBefore"
        in 45*MINUTE+1 .. 75*MINUTE -> "${eventAfter}час$eventBefore"
        in 75*MINUTE+1 .. 22*HOUR -> "$eventAfter${Utils.parseTime(diffTime / HOUR, TimeUnits.HOUR)}$eventBefore"
        in 22*HOUR+1 .. 26* HOUR -> "${eventAfter}день$eventBefore"
        in 26*HOUR+1 .. 360*DAY -> "$eventAfter${Utils.parseTime(diffTime / DAY, TimeUnits.DAY)}$eventBefore"
        else -> "более ${if (eventAfter !="") "чем через год" else "года$eventBefore"}"
    }
}

fun Date.add(value:Int, units:TimeUnits = TimeUnits.SECOND): Date{
    var time = this.time
    time += when(units){
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        TimeUnits.YEAR -> value * YEAR
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECOND,
    MINUTE,
    HOUR,
    DAY,
    YEAR;

    fun plural(value: Int): String {
        return Utils.parseTime(value.toLong(),this)
    }
}