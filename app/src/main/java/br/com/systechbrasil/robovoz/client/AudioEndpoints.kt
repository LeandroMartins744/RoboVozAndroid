package br.com.systechbrasil.robovoz.client

import br.com.systechbrasil.robovoz.model.request.AudioRequest
import br.com.systechbrasil.robovoz.model.response.AudioResponse
import okhttp3.ResponseBody
import retrofit2.http.*
import java.io.OutputStream

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

    @Streaming
    @GET("audio/download/{id}")
    suspend fun download(@Header("Authorization") authKey: String, @Path("id") id: String): ResponseBody
}