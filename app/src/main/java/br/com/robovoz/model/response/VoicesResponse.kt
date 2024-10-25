package br.com.robovoz.model.response

import com.google.gson.annotations.SerializedName

data class VoicesResponse (
    @SerializedName("voice_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("category")
    var category: String,
    @SerializedName("preview_url")
    var preview_url: String,
){
    constructor(): this("", "", "", "")
}