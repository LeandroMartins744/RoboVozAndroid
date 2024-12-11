package com.example.myapplication.model.response

import com.google.gson.annotations.SerializedName

class ClientResponse (
    @SerializedName("id")
    var id: Int,

    @SerializedName("name")
    var name: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("phone")
    var phone: String,

    @SerializedName("birthDay")
    var birthDay: String,

    @SerializedName("cep")
    var cep: String,

    @SerializedName("street")
    var street: String,

    @SerializedName("number")
    var number: String,

    @SerializedName("complement")
    var complement: String,

    @SerializedName("neighborhood")
    var neighborhood: String,

    @SerializedName("city")
    var city: String,

    @SerializedName("state")
    var state: String,

    @SerializedName("company")
    var company: String,

    @SerializedName("status")
    var status: String,

    @SerializedName("codeValidation")
    var codeValidation: String,

    @SerializedName("active")
    var active: String
){
    constructor(): this(0, "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
}