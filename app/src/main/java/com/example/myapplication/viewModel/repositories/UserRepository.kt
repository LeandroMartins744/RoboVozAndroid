package com.example.myapplication.viewModel.repositories

import android.util.Log
import com.example.myapplication.model.response.UserResponse
import com.example.myapplication.client.AuthTokenService
import com.example.myapplication.client.RetrofitInitializer
import com.example.myapplication.client.UsersEndpoints
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    private val userCall = RetrofitInitializer.getInstance().create(UsersEndpoints::class.java)

    fun getAll(): List<UserResponse> {
        var data: List<UserResponse> = mutableListOf()
        userCall.getUsersList(AuthTokenService().getAuthToken())
            .enqueue(object: Callback<List<UserResponse>>{
                override fun onResponse(call: Call<List<UserResponse>>, response: Response<List<UserResponse>>) {
                    Log.d("TAG", "onResponse response:: $response")

                    if(response.body() != null){
                        data = response.body()!!
                    }
                }

                override fun onFailure(call: Call<List<UserResponse>>, response: Throwable) {
                    Log.d("TAG ERROS", "onResponse response:: $response")
                    data = emptyList()
                }
            })
        return  data
    }
}