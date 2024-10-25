package br.com.robovoz.model.request

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
    val playlist: Int

){
    constructor(name: String, description: String, audio: String): this( name, description, audio, 1)
}