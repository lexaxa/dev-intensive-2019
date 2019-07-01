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
        in 0..5 -> "только что"
        in 5..MINUTE -> "$eventAfter${Utils.parseTime(diffTime, TimeUnits.SECONDS)}$eventBefore"
        in MINUTE..HOUR -> "$eventAfter${Utils.parseTime(diffTime / MINUTE, TimeUnits.MINUTE)}$eventBefore"
        in HOUR..DAY -> "$eventAfter${Utils.parseTime(diffTime / HOUR, TimeUnits.HOUR)}$eventBefore"
        in DAY..365*DAY -> "$eventAfter${Utils.parseTime(diffTime / DAY, TimeUnits.DAY)}$eventBefore"
        else -> "более ${if (eventAfter !="") "чем через год" else "года$eventBefore"}"
    }
}

fun Date.add(value:Int, units:TimeUnits = TimeUnits.SECONDS): Date{
    var time = this.time
    time += when(units){
        TimeUnits.SECONDS -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
        TimeUnits.YEAR -> value * YEAR
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECONDS,
    MINUTE,
    HOUR,
    DAY,
    YEAR
}