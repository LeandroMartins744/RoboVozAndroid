package com.example.myapplication.model.request

import com.google.gson.annotations.SerializedName

data class ClientResultRequest(
    var name: String,
    var email: String,
    var status: Int,
    var message: String
){
    constructor(): this(
        name = "",
        email = "",
        status = 0,
        message = ""
    )
}