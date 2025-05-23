package com.example.myapplication.model.request

import android.os.Build
import com.google.gson.annotations.SerializedName

data class UserNotifyRequest(
    @SerializedName("userId")
    var userId: Int,

    @SerializedName("marca")
    val marca: String = Build.BRAND,

    @SerializedName("model")
    val model: String = Build.MODEL,

    @SerializedName("token")
    val token: String
) {
    constructor(_userId: Int, _token: String): this(userId = _userId, token = _token){}
}