package com.example.myapplication.client

import com.example.myapplication.model.request.AudioRequest
import com.example.myapplication.model.response.AudioResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AudioEndpoints {
    @GET("audio")
    suspend fun get(@Header("Authorization") authKey: String): List<AudioResponse>

    @GET("audio/{id}")
    suspend fun get(@Header("Authorization") authKey: String, @Path("id") id: Int): AudioResponse

    @POST("audio")
    suspend fun post(@Header("Authorization") authKey: String, @Body body: AudioRequest): AudioResponse

    @PUT("audio")
    suspend fun put(@Header("Authorization") authKey: String, @Body body: AudioRequest): AudioResponse

    @DELETE("audio/{id}")
    suspend fun delete(@Header("Authorization") authKey: String, @Path("id") id: Int): Int
}