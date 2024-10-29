package com.example.myapplication.client

import com.example.myapplication.model.request.PlayListRequest
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.model.response.UserRequest
import com.example.myapplication.model.response.UserResponse
import com.example.myapplication.model.response.VoicesResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface VoicesEndpoints {
    @GET("voices")
    suspend fun get(@Header("Authorization") authKey: String): List<VoicesResponse>
}