package com.example.myapplication.client

import com.example.myapplication.model.request.SchedulingRequest
import com.example.myapplication.model.response.SchedulingResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SchedulingEndpoints {
    @GET("scheduling")
    suspend fun get(@Header("Authorization") authKey: String): List<SchedulingResponse>

    @GET("scheduling/{id}")
    suspend fun get(@Header("Authorization") authKey: String, @Path("id") id: Int): SchedulingResponse

    @POST("scheduling")
    suspend fun post(@Header("Authorization") authKey: String, @Body body: SchedulingRequest): SchedulingResponse

    @PUT("scheduling")
    suspend fun put(@Header("Authorization") authKey: String, @Body body: SchedulingRequest): SchedulingResponse

    @DELETE("scheduling/{id}")
    suspend fun delete(@Header("Authorization") authKey: String, @Path("id") id: Int): Int
}