package com.example.myapplication.client

import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.util.BASE_URL
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


