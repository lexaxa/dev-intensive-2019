package ru.skillbranch.devintensive.utils

import android.content.Context
import android.content.res.Configuration
import android.util.TypedValue
import ru.skillbranch.devintensive.extensions.TimeUnits

object Utils{
    /**
    Example:
    Utils.parseFullName(null) //null null
    Utils.parseFullName("") //null null
    Utils.parseFullName(" ") //null null
    Utils.parseFullName("John") //John null
    */
    fun parseFullName( fullName: String?):Pair<String?, String?>{
        val parts : List<String>? = fullName?.trim()?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return (if(firstName.isNullOrBlank()) null else firstName) to lastName
    }
    /**
     * Example:
     * Utils.toInitials("john" ,"doe") //JD
     * Utils.toInitials("John", null) //J
     * Utils.toInitials(null, null) //null
     * Utils.toInitials(" ", "") //null
     */
    fun toInitials(firstName: String?, lastName: String?): String?{
        val fn = (if (firstName.isNullOrBlank()) "" else firstName[0].toUpperCase()).toString()
        val ln = (if (lastName.isNullOrBlank()) ""  else lastName[0].toUpperCase()).toString()

        return if(fn!="" || ln!="") "$fn$ln" else null

    }
    /**
     * Example:
     * Utils.transliteration("Иван Стереотипов") //Ivan Stereotipov
     * Utils.transliteration("Amazing Петр","_") //Amazing_Petr
     */
    fun transliteration(payload: String, divider: String = " "): String{
        val chars : Map<Char, String> = mapOf(
            'а' to "a",     'б' to "b",     'в' to "v",     'г' to "g",
            'д' to "d",     'е' to "e",     'ё' to "e",     'ж' to "zh",
            'з' to "z",     'и' to "i",     'й' to "i",     'к' to "k",
            'л' to "l",     'м' to "m",     'н' to "n",     'о' to "o",
            'п' to "p",     'р' to "r",     'с' to "s",     'т' to "t",
            'у' to "u",     'ф' to "f",     'х' to "h",     'ц' to "c",
            'ч' to "ch",    'ш' to "sh",    'щ' to "sh'",   'ъ' to "",
            'ы' to "i",     'ь' to "",      'э' to "e",     'ю' to "yu",
            'я' to "ya"
        )
        var trans = ""
        for(c in payload){
            when {
                chars.containsKey(c) -> trans += chars[c]
                chars.containsKey(c.toLowerCase()) -> trans += chars[c.toLowerCase()]?.toUpperCase()
                c==' ' -> trans += divider
                else -> trans += c
            }
        }

        return trans
    }
    fun parseTime(value: Long, unit: TimeUnits): String{
        val mod = (value % 10).toInt()
        return when(unit){
            TimeUnits.SECOND -> {
                "$value секунд" + when(mod){
                    1 -> "у"
                    2,3,4 -> "ы"
                    else -> ""
                }
            }
            TimeUnits.MINUTE -> {
                when(mod){
                    0, in 5..9 -> "$value минут"
                    1 -> "$value минуту"
                    2,3,4 -> "$value минуты"
                    else -> "$value минут"
                }
            }
            TimeUnits.HOUR -> {
                when(mod){
                    0, in 5..9 -> "$value часов"
                    1 -> "$value час"
                    2,3,4 -> "$value часа"
                    else -> "$value часов"
                }
            }
            TimeUnits.DAY -> {
                when(mod){
                    0, in 5..9 -> "$value дней"
                    1 -> "$value день"
                    2,3,4 -> "$value дня"
                    else -> "$value дней"
                }
            }
            TimeUnits.YEAR -> {
                when(mod){
                    0, in 5..9 -> "$value лет"
                    1 -> "$value год"
                    2,3,4 -> "$value года"
                    else -> "$value лет"
                }
            }
        }
    }
    fun convertDpToPx(context: Context, dp: Float): Float {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                context.resources.displayMetrics
        )
    }

    fun getCurrentModeColor(context: Context, attrColor: Int): Int {
        val value = TypedValue()
        context.theme.resolveAttribute(attrColor, value, true)
        return value.data
    }

    fun isNightModeActive(context: Context) : Boolean {
        return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }
    fun convertSpToPx(context: Context, sp: Int) :Int{
        return sp * context.resources.displayMetrics.scaledDensity.toInt()
    }
}