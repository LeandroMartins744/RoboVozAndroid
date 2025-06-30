package br.com.systechbrasil.robovoz.model.request

import com.google.gson.annotations.SerializedName

data class ClientRequest(
    var name: String,
    var email: String,
    var phone: String,
    var birthDay: String,
    var cep: String,
    var street: String,
    var number: String,
    var complement: String,
    var neighborhood: String,
    var city: String,
    var state: String,
    var company: String
){
    constructor(): this(
        name = "",
        email = "",
        phone = "",
        birthDay = "",
        cep = "",
        street = "",
        number = "",
        complement = "",
        neighborhood = "",
        city = "",
        state = "",
        company = ""
    )
}