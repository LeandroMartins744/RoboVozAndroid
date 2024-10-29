package com.example.myapplication.model.response

import com.google.gson.annotations.SerializedName
import java.util.*

data class SchedulingResponse (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("playlist")
    val playList: PlayListResponse,
    @SerializedName("date")
    val date: Date,
    @SerializedName("idUser")
    val idUser: Int,
    @SerializedName("started")
    val started: Boolean,
    @SerializedName("dateStarted")
    val dateStarted: Date,
    @SerializedName("finish")
    val finish: Boolean,
    @SerializedName("dateFinish")
    val dateFinish: Date,
    @SerializedName("sendEvent")
    val sendEvent: Boolean
){
    constructor(): this(0, "", PlayListResponse(), Date(), 0, false, Date(), false, Date(), false)
}