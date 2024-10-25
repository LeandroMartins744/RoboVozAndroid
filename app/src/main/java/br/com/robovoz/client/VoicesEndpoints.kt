package br.com.robovoz.client

import br.com.robovoz.model.response.VoicesResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface VoicesEndpoints {
    @GET("voices")
    suspend fun get(@Header("Authorization") authKey: String): List<VoicesResponse>
}