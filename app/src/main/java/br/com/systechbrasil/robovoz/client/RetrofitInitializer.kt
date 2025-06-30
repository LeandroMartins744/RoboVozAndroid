package br.com.systechbrasil.robovoz.client

import br.com.systechbrasil.robovoz.model.response.PlayListResponse
import br.com.systechbrasil.robovoz.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header


object RetrofitInitializer {
    private var INSTANCE: Retrofit? = null

    fun getInstance(): Retrofit = INSTANCE ?: kotlin.run {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

interface ApiService {
    companion object {
        //  private var apiService: ApiService? = null
        fun getInstance(): Retrofit {
            // if (apiService == null) {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        //  return apiService!!
    }
}


