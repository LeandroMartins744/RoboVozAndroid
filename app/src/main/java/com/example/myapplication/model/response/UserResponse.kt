package com.example.myapplication.model.response

import com.example.myapplication.model.ErrorModel
import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("firstAccess")
    val firstAccess: Boolean,
    @SerializedName("active")
    val active: Boolean
): ErrorModel()

data class UserRequest(
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)