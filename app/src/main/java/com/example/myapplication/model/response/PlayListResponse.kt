package com.example.myapplication.model.response

import com.google.gson.annotations.SerializedName

data class PlayListResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("audios")
    val audios: List<AudioResponse>,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("active")
    val active: Boolean
){
    constructor(): this(0, emptyList(), "", "", "", "", false)
}