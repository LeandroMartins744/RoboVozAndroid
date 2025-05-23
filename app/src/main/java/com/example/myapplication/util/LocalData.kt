package com.example.myapplication.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.myapplication.model.request.UserNotifyRequest
import com.example.myapplication.model.response.UserResponse
import com.example.myapplication.model.response.VoicesResponse
import com.google.gson.Gson

class LocalData(var context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(DATA_DB_USERS_LOCAL, MODE_PRIVATE)

    fun setLoop(value: Boolean){
        sharedPref.edit().putBoolean(DATA_LOOP_PLAYER_LOCAL, value).apply()
    }

    fun getLoop(): Boolean{
        val data = sharedPref.getBoolean(DATA_LOOP_PLAYER_LOCAL, false)
        return data
    }

    fun set(user: UserResponse){
        val data = Gson().toJson(user)
        sharedPref.edit().putString(DATA_USERS_LOCAL, data).apply()
    }

    fun get(): UserResponse{
        val data = sharedPref.getString(DATA_USERS_LOCAL, null)
        return Gson().fromJson(data, UserResponse::class.java)
    }

    fun getVoice(): VoicesResponse{
        val data = sharedPref.getString(DATA_VOICE_DEFAULT, null) ?: return VoicesResponse()
        return Gson().fromJson(data, VoicesResponse::class.java)
    }

    fun setVoice(obj: VoicesResponse){
        val data = Gson().toJson(obj)
        sharedPref.edit().putString(DATA_VOICE_DEFAULT, data).apply()
    }

    fun setTokenFireBase(obj: UserNotifyRequest){
        val data = Gson().toJson(obj)
        sharedPref.edit().putString(DATA_USERS_TOKEN_FIREBASE, data).apply()
    }

    fun getTokenFireBase(): UserNotifyRequest{
        val data = sharedPref.getString(DATA_USERS_TOKEN_FIREBASE, null) ?: return UserNotifyRequest(0, "")
        return Gson().fromJson(data, UserNotifyRequest::class.java)
    }

    fun clean(){
        sharedPref.edit().clear().apply()
    }

    fun valid(): Boolean {
        val data = sharedPref.getString(DATA_USERS_LOCAL, null)
        return data.isNullOrBlank()
    }
}