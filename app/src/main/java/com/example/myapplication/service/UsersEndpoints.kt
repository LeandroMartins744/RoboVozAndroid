package com.example.myapplication.service

import com.example.myapplication.data.response.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UsersEndpoints {
    @GET("user")
    fun getUsersList(@Header("Authorization") authkey: String): Call<List<UserResponse>>

    @GET("user")
    fun getUsersList2(): List<UserResponse>

//    @GET("api/v1/employee/{employeeId}")
//    suspend fun getEmployeeById(@Path("employeeId") employeeId: Int): Employee
//
//    @GET("api/v1/employees")
//    suspend fun getEmployeeListRequireToken(@Header("x-api-key") key: String): List<Employee>
}