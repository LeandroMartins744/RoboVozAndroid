package com.example.myapplication.client

import com.example.myapplication.model.request.UserNotifyRequest
import com.example.myapplication.model.response.UserRequest
import com.example.myapplication.model.response.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface UsersEndpoints {
    @GET("user")
    fun getUsersList(@Header("Authorization") authkey: String): Call<List<UserResponse>>

    @POST("user/loginUser")
    fun getUserLogin(@Header("Authorization") authkey: String, @Body body: UserRequest): Call<UserResponse>

    @POST("user/userNotification")
    fun getUserNotify(@Header("Authorization") authkey: String, @Body body: UserNotifyRequest): Call<UserNotifyRequest>

    @GET("user")
    fun getUsersList2(): List<UserResponse>
}