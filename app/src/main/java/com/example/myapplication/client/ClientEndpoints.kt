package com.example.myapplication.client

import com.example.myapplication.model.request.AudioRequest
import com.example.myapplication.model.request.ClientRequest
import com.example.myapplication.model.request.ClientResultRequest
import com.example.myapplication.model.response.AudioResponse
import com.example.myapplication.model.response.ClientResponse
import okhttp3.ResponseBody
import retrofit2.http.*
import java.io.OutputStream

interface ClientEndpoints {
    @GET("client/{id}")
    suspend fun get(@Header("Authorization") authKey: String, @Path("id") id: Int): ClientResponse

    @POST("client")
    suspend fun post(@Header("Authorization") authKey: String, @Body body: ClientRequest): ClientResultRequest

    @PUT("client")
    suspend fun put(@Header("Authorization") authKey: String, @Body body: ClientRequest): ClientResponse

    @DELETE("client/{id}")
    suspend fun delete(@Header("Authorization") authKey: String, @Path("id") id: Int): Int

}