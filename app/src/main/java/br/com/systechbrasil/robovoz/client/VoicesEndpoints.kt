package br.com.systechbrasil.robovoz.client

import br.com.systechbrasil.robovoz.model.request.PlayListRequest
import br.com.systechbrasil.robovoz.model.response.PlayListResponse
import br.com.systechbrasil.robovoz.model.response.UserRequest
import br.com.systechbrasil.robovoz.model.response.UserResponse
import br.com.systechbrasil.robovoz.model.response.VoicesResponse
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