package com.example.myapplication.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.example.myapplication.model.response.UserResponse
import com.google.gson.Gson

class LocalData(var context: Context) {
    private val sharedPref: SharedPreferences = context.getSharedPreferences(DATA_DB_USERS_LOCAL, MODE_PRIVATE)

    fun set(user: UserResponse){
        val data = Gson().toJson(user)
        sharedPref.edit().putString(DATA_USERS_LOCAL, data).commit()
    }

    fun get(): UserResponse{

        val data = sharedPref.getString(DATA_USERS_LOCAL, null)
        //return Gson().toO
    }

    fun valid(): Boolean {

        val data = sharedPref.getString(DATA_USERS_LOCAL, null)
        return data.isNullOrBlank()
    }
}