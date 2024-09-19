package com.example.myapplication.client

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


    @GET("user")
    fun getUsersList2(): List<UserResponse>



//    @GET("api/v1/employee/{employeeId}")
//    suspend fun getEmployeeById(@Path("employeeId") employeeId: Int): Employee
//
//    @GET("api/v1/employees")
//    suspend fun getEmployeeListRequireToken(@Header("x-api-key") key: String): List<Employee>
}