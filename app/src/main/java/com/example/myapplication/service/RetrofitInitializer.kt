package com.example.myapplication.service

import com.example.myapplication.data.PlayListModel
import com.example.myapplication.util.BASE_URL
import com.example.myapplication.util.PASS_REST
import com.example.myapplication.util.USER_REST
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInitializer {
    private var INSTANCE: Retrofit? = null

    fun getInstance(): Retrofit = INSTANCE ?: kotlin.run {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
