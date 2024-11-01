package com.example.myapplication.model

abstract class ErrorModel (var codError: Int, var isError: Boolean = false, var messageError: String, var throwable: Throwable?){
    constructor(): this(0, false, "", null)
}