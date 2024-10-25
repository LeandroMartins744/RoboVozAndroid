package com.example.robovoz.util

import android.os.Build
import androidx.annotation.RequiresApi
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

    fun getStart(obj: Boolean) = if(obj) "Sim" else "Não"
}