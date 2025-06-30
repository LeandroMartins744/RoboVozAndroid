package br.com.systechbrasil.robovoz.model.request

import com.google.gson.annotations.SerializedName
import java.util.*

data class SchedulingRequest(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("playlist")
    val playlist: PlayListRequest,
    @SerializedName("date")
    val date: String,
    @SerializedName("iduser")
    val idUser: Int,
    @SerializedName("started")
    val started: Boolean = false,
    @SerializedName("dateStarted")
    val dateStarted: Date? = null,
    @SerializedName("finish")
    val finish: Boolean = false,
    @SerializedName("dateFinish")
    val dateFinish: Date? = null,
    @SerializedName("sendEvent")
    val sendEvent: Boolean = false
){
    constructor(_name: String, _playlist: PlayListRequest, _date: String, _idUser: Int) :
            this(name = _name, id = 0, playlist =  _playlist, date = _date,
            idUser = 1) {

    }
}