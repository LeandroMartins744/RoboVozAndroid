package com.example.myapplication.client

import com.example.myapplication.model.request.PlayListRequest
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.model.response.UserRequest
import com.example.myapplication.model.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PlaylistEndpoints {
    @GET("playlist")
    suspend fun get(@Header("Authorization") authKey: String): List<PlayListResponse>

    @GET("playlist/{id}")
    suspend fun get(@Header("Authorization") authKey: String, @Path("id") id: Int): PlayListResponse

    @POST("playlist")
    suspend fun post(@Header("Authorization") authKey: String, @Body body: PlayListRequest): PlayListResponse

    @PUT("playlist")
    suspend fun put(@Header("Authorization") authKey: String, @Body body: PlayListRequest): PlayListResponse

    @DELETE("playlist/{id}")
    suspend fun delete(@Header("Authorization") authKey: String, @Path("id") id: Int): Int
}