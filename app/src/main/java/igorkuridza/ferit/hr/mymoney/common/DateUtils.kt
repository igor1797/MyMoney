package igorkuridza.ferit.hr.mymoney.common

import android.annotation.SuppressLint
import igorkuridza.ferit.hr.mymoney.model.recyclerview.Date
import java.text.SimpleDateFormat
import java.util.*


fun convertDate(date: String): Date {
    val parts = date.split(" ")
    return Date(parts.first().toInt(), parts[1], parts[2].toInt())
}

fun getYearFromMonthYear(monthYear: String): Int{
    val parts = monthYear.split(" ")
    return parts[1].toInt()
}

fun getMonthFromMonthYear(monthYear: String): String{
    val parts = monthYear.split(" ")
    return parts[0]
}

fun getDay(date: String): Int{
    val parts = date.split(" ")
    return parts.first().toInt()
}

fun getMonth(date: String): String{
    val parts = date.split(" ")
    return parts[1]
}

fun getYear(date: String): Int{
    val parts = date.split(" ")
    return parts[2].toInt()
}

@SuppressLint("SimpleDateFormat")
fun getTodayDate(): String{
    val calendar: Calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat("d MMMM yyyy")
    return formatter.format(Date(calendar.timeInMillis))
}

@SuppressLint("SimpleDateFormat")
fun getCurrentDayOfMonth(): Int{
    val calendar: Calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat("d")
    return formatter.format(Date(calendar.timeInMillis)).toInt()
}

@SuppressLint("SimpleDateFormat")
fun getCurrentMonth(): String{
    val calendar: Calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat("MMMM yyyy")
    return formatter.format(Date(calendar.timeInMillis))
}

@SuppressLint("SimpleDateFormat")
fun getCurrentMonthNumber(): Int{
    val calendar: Calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat("M")
    return formatter.format(Date(calendar.timeInMillis)).toInt()
}

@SuppressLint("SimpleDateFormat")
fun getCurrentYear(): String{
    val calendar: Calendar = Calendar.getInstance()
    val formatter = SimpleDateFormat("yyyy")
    return formatter.format(Date(calendar.timeInMillis))
}

@SuppressLint("SimpleDateFormat")
fun convertDateToMilliseconds(date: String): Long{
    val formatter = SimpleDateFormat("d MMMM yyyy")
    val mDate = formatter.parse(date)
    return mDate!!.time
}

@SuppressLint("SimpleDateFormat")
fun convertMilliSecondsToDate(time: Long): String{
    val formatter = SimpleDateFormat("d MMMM yyyy")
    return formatter.format(Date(time))
}
