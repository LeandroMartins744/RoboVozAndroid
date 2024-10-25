package com.example.robovoz.client

import com.example.robovoz.model.response.VoicesResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface VoicesEndpoints {
    @GET("voices")
    suspend fun get(@Header("Authorization") authKey: String): List<VoicesResponse>
}