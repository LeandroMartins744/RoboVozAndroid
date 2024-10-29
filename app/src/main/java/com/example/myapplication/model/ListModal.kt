package com.example.myapplication.model

data class ListModal(

    val languageName: String,
    val description: String = "",
    val languageImg: Int
)

data class ListAudiosModal(

    val name: String,
    val image: Int,
    val description: String = "",
    val audioExample: Int
)