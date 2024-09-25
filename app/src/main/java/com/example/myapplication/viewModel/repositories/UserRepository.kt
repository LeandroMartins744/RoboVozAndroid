package com.example.myapplication.viewModel.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.model.response.UserResponse
import com.example.myapplication.client.AuthTokenService
import com.example.myapplication.client.RetrofitInitializer
import com.example.myapplication.client.UsersEndpoints
import com.example.myapplication.model.response.UserRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    private val userCall = RetrofitInitializer.getInstance().create(UsersEndpoints::class.java)

    fun getUser(user: UserRequest, userData: MutableLiveData<UserResponse>){
        userCall.getUserLogin(AuthTokenService().getAuthToken(), user)
            .enqueue(object: Callback<UserResponse>{
                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                    Log.d("TAG", "onResponse response:: $response")

                    if(response.body() != null)
                        userData.value = response.body()!!
                }

                override fun onFailure(call: Call<UserResponse>, response: Throwable) {
                    Log.d("TAG ERROS", "onResponse response:: $response")
                    userData.value = null
                }
            })
    }
}