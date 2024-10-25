package br.com.robovoz.client

import android.util.Base64
import br.com.robovoz.util.PASS_REST
import br.com.robovoz.util.USER_REST
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