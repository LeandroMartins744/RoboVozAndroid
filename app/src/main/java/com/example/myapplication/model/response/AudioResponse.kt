package com.example.myapplication.model.response

import android.media.MediaPlayer
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
    var date: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("active")
    val active: Boolean,

){
    constructor(): this(0, "", "", "", "", "", "", false)
}
