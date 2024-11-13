package com.example.myapplication.model.response

import com.google.gson.annotations.SerializedName

data class PlayListResponse (
    @SerializedName("id")
    var id: Int,
    @SerializedName("audios")
    var audios: ArrayList<AudioResponse>,
    @SerializedName("name")
    var name: String,
    @SerializedName("description")
    var description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("date")
    var date: String,
    @SerializedName("active")
    val active: Boolean
){
    constructor(): this(0, ArrayList<AudioResponse>(), "", "", "", "", false)
}