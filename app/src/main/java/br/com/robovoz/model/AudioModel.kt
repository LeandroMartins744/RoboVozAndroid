package br.com.robovoz.model

import java.util.*

data class AudioModel(
    var id: Long? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null,
    val date: Date? = null,
    val active: Boolean = false
)