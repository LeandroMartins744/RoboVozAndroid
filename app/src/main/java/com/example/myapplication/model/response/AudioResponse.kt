package com.example.myapplication.model.response

import com.google.gson.annotations.SerializedName

data class AudioResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("audio")
    val audio: String,
    @SerializedName("audioFile")
    val audioFile: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("active")
    val active: Boolean

){
}