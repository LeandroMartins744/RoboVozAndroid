package com.example.myapplication.service

import com.example.myapplication.data.PlayListModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {
    var urlGet: String = "https://66cb90124290b1c4f19aa171.mockapi.io/api/"
    var _urlGet: String = "https://66cb90124290b1c4f19aa171.mockapi.io/api/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(_urlGet)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun playList(): PlayListService{
        return retrofit.create(PlayListService::class.java)
    }
}
