package com.example.myapplication.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DateFormat {
    fun getFormat(date: String): String{
        //return date
        return "23/09/2024 10:12"
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormat(date: Date): String{
        return "${date.day}/${date.month}/${date.year} ${date.hours}:${date.minutes}"
    }

    fun getDate(pattern: String = "dd/MM/yyyy"): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(Date())

    }

    fun getDateFormat(value: Long, pattern: String = "dd/MM/yyyy"): String {
        val date = Date(value)
        val formatter = SimpleDateFormat(pattern, Locale("pt-br")
        ).apply { timeZone = TimeZone.getTimeZone("GMT") }
        return formatter.format(date)
    }

    fun getStart(obj: Boolean) = if(obj) "Sim" else "Não"
}