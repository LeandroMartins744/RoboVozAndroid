package com.example.myapplication.util

import android.annotation.SuppressLint
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

    @SuppressLint("SimpleDateFormat")
    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormatUS(date: Date): String{
        return SimpleDateFormat("yyyy-MM-dd hh:mm").format(date).replace(" ", "T")
    }

    fun getDate(pattern: String = "dd/MM/yyyy hh:mm"): String {
        val formatter = SimpleDateFormat(pattern, Locale.getDefault())
        return formatter.format(Date())
    }
    fun getDate(date: Date, pattern: String = "dd/MM/yyyy hh:mm"): String {
        val formatter = SimpleDateFormat(pattern, Locale.US)// .getDefault())
        return formatter.format(date)
    }

    fun getDate(day: String, month: String, year: String, hour: String, min: String): Date{
        val mon = (month.toInt() + 1).toString()
        return Date("$year/$mon/$day $hour:$min")
    }

    fun getDate(day: String, month: String, year: String): Date{
        return Date("$year/$month/$day")
    }

    fun getDateFormat(value: Long, pattern: String = "dd/MM/yyyy"): String {
        val date = Date(value)
        val formatter = SimpleDateFormat(pattern, Locale("pt-br")
        ).apply { timeZone = TimeZone.getTimeZone("GMT") }
        return formatter.format(date)
    }

    fun getStart(obj: Boolean) = if(obj) "Sim" else "Não"

    fun getDays() = arrayOf(
        "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
        "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"
    )

    fun getMonth() = arrayOf(
        "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
        "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
    )

    fun getYear() = arrayOf(
        "2024", "2025", "2026"
    )

    fun getHour() = arrayOf(
        "01", "02", "03", "04", "05", "06", "07", "08", "09", "10",
        "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
        "21", "22", "23", "00"
    )

    fun getMinutos() = arrayOf(
        "00", "10", "20", "30", "40", "50"
    )

    fun getStates() = arrayOf(
        "Acre", "Alagoas", "Amapá", "Amazonas", "Bahia", "Ceará", "Distrito Federal", "Espirito Santo", "Goiás", "Maranhão", "Mato Grosso do Sul", "Mato Grosso", "Minas Gerais", "Pará", "Paraíba", "Paraná", "Pernambuco", "Piauí", "Rio de Janeiro", "Rio Grande do Norte", "Rio Grande do Sul", "Rondônia", "Roraima", "Santa Catarina", "São Paulo", "Sergipe", "Tocantins"
    )
}