package com.example.robovoz.client

import com.example.robovoz.model.request.PlayListRequest
import com.example.robovoz.model.response.PlayListResponse
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