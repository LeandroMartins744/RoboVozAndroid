package com.example.myapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.myapplication.client.*
import com.example.myapplication.model.request.SchedulingRequest
import com.example.myapplication.model.response.SchedulingResponse
import com.example.myapplication.util.LodData

import kotlinx.coroutines.launch

class SchedulingViewModel() : ViewModel() {
    var loading by mutableStateOf(true)
        private set
    var schedulingListResponse:List<SchedulingResponse> by mutableStateOf(listOf())
    var schedulingResponse: SchedulingResponse by mutableStateOf(SchedulingResponse())
    private val apiService = ApiService.getInstance().create(SchedulingEndpoints::class.java)

    fun get() {
        viewModelScope.launch {
            try {
                val movieList = apiService.get(AuthTokenService().getAuthToken())
                schedulingListResponse = movieList
                loading = false
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun get(id: Int) {
        viewModelScope.launch {
            try {
                val item = apiService.get(AuthTokenService().getAuthToken(), id)
                schedulingResponse = item
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun post(obj: SchedulingRequest) {
        loading = true
        viewModelScope.launch {
            try {
                val item = apiService.post(AuthTokenService().getAuthToken(), obj)
                schedulingResponse = item
                loading = false
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun put(obj: SchedulingRequest) {
        viewModelScope.launch {
            try {
                val item = apiService.put(AuthTokenService().getAuthToken(), obj)
                schedulingResponse = item
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun delete(id: Int) {
        viewModelScope.launch {
            try {
                apiService.delete(AuthTokenService().getAuthToken(), id)
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }
}