package br.com.systechbrasil.robovoz.client

import br.com.systechbrasil.robovoz.model.request.AudioRequest
import br.com.systechbrasil.robovoz.model.request.ClientRequest
import br.com.systechbrasil.robovoz.model.request.ClientResultRequest
import br.com.systechbrasil.robovoz.model.response.AudioResponse
import br.com.systechbrasil.robovoz.model.response.ClientResponse
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