package com.example.robovoz.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.robovoz.client.*
import com.example.robovoz.model.response.VoicesResponse
import com.example.robovoz.util.LodData

import kotlinx.coroutines.launch

class VoicesViewModel() : ViewModel() {
    var loading by mutableStateOf(true)
        private set
    var voicesResponse: List<VoicesResponse> by mutableStateOf(listOf())
    private val apiService = ApiService.getInstance().create(VoicesEndpoints::class.java)

    fun get() {
        viewModelScope.launch {
            try {
                val movieList = apiService.get(AuthTokenService().getAuthToken())
                voicesResponse = movieList
                loading = false
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }
}