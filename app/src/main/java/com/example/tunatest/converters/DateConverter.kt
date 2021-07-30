package com.example.tunatest.converters

import java.text.SimpleDateFormat
import java.util.*


fun convertDateToString(date: String): String {
    val elements = date.split("/")
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, elements[0].toInt() - 1)
    calendar.set(Calendar.DAY_OF_MONTH, elements[1].toInt())
    calendar.set(Calendar.YEAR, elements[2].toInt())
    val date = Date(calendar.timeInMillis)
    val sdf = SimpleDateFormat("MMM yyyy")
    return sdf.format(date)
}

fun convertDateToDayString(date: String): String {
    val elements = date.split("/")
    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, elements[0].toInt() - 1)
    calendar.set(Calendar.DAY_OF_MONTH, elements[1].toInt())
    calendar.set(Calendar.YEAR, elements[2].toInt())
    val date = Date(calendar.timeInMillis)
    val sdf = SimpleDateFormat("EE dd")
    return sdf.format(date)
}