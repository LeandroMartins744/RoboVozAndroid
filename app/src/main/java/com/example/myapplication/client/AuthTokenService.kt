package com.example.myapplication.client

import android.util.Base64
import com.example.myapplication.util.PASS_REST
import com.example.myapplication.util.USER_REST
import java.io.UnsupportedEncodingException

class AuthTokenService(){
    fun getAuthToken(): String {
        var data = ByteArray(0)
        try {
            data = ("$USER_REST:$PASS_REST").toByteArray(charset("UTF-8"))
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        return "Basic " + Base64.encodeToString(data, Base64.NO_WRAP)
    }
}