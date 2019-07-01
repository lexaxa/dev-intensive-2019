package ru.alexoheah.devintensive.extensions

import ru.alexoheah.devintensive.utils.Utils
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"):String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.humanizeDiff(date: Date): String{
    val diff: String = "только что"
    val diffTime = date.time - this.time
    println("${date.format()} - ${this.format()} -> ${date.time - this.time} = $diffTime")
    //TODO Сделать склонение слов
    return when (diffTime) {
        in 0..5 -> "только что"
        in 5..MINUTE -> "${Utils.parseTime(diffTime, TimeUnits.SECONDS)} назад"
        in MINUTE..HOUR -> "${Utils.parseTime(diffTime / MINUTE, TimeUnits.MINUTE)} минут назад"
        in HOUR..DAY -> "${Utils.parseTime(diffTime / HOUR, TimeUnits.HOUR)} часов назад"
        in DAY..365*DAY -> "${Utils.parseTime(diffTime / DAY, TimeUnits.DAY)} дней назад"
        else -> date.format()
    }
}

fun Date.add(value:Int, units:TimeUnits = TimeUnits.SECONDS): Date{
    var time = this.time
    time += when(units){
        TimeUnits.SECONDS -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

enum class TimeUnits{
    SECONDS,
    MINUTE,
    HOUR,
    DAY
}