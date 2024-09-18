package com.example.myapplication.service

import com.example.myapplication.data.PlayListModel
import retrofit2.Call
import retrofit2.http.GET

interface PlayListService{
    @GET("playlist/playlist")
    fun list(): Call<List<PlayListModel>>
}