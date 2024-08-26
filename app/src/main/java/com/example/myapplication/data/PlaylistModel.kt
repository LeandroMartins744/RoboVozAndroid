package com.example.myapplication.data

import java.util.*

data class PlayListModel (
    val id: Long? = null,
    val audios: List<AudioModel>,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val date: Date? = null,
    val active: Boolean = false
)
