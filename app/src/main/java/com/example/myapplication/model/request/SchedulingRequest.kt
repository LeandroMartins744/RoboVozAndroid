package com.example.myapplication.model.request

import com.google.gson.annotations.SerializedName
import java.util.*

data class SchedulingRequest (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("playlist")
    val playlist: PlayListRequest,
    @SerializedName("date")
    val date: Date,
    @SerializedName("iduser")
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

}