package com.example.myapplication.model.request

import com.google.gson.annotations.SerializedName

data class AudioRequest (
    //@SerializedName("id")
    //val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("audio")
    var audio: String,
    @SerializedName("playlist")
    var playlist: Int,
    @SerializedName("voice")
    val voice: String,

){
    constructor(name: String, description: String, audio: String, voice: String): this( name, description, audio, 1, voice)
}