package com.example.myapplication.model.request

import com.google.gson.annotations.SerializedName

data class PlayListRequest (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("active")
    val active: Boolean
){
    constructor(name: String, desc: String): this(0, name = name, description = desc, image = "", active = true)
    constructor(id: Int, name: String, desc: String): this(id, name = name, description = desc, image = "", active = true)
}