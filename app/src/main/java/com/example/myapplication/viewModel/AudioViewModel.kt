package com.example.myapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.myapplication.client.*
import com.example.myapplication.model.request.AudioRequest
import com.example.myapplication.model.request.PlayListRequest
import com.example.myapplication.model.response.AudioResponse
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.util.DateFormat
import com.example.myapplication.util.LodData

import kotlinx.coroutines.launch

class AudioViewModel() : ViewModel() {
    var loading by mutableStateOf(true)
        private set
    var itemListResponse:List<AudioResponse> by mutableStateOf(listOf())
    var itemResponse: AudioResponse by mutableStateOf(AudioResponse())
    private val apiService = ApiService.getInstance().create(AudioEndpoints::class.java)

    fun get() {
        viewModelScope.launch {
            try {
                val list = apiService.get(AuthTokenService().getAuthToken())
                itemListResponse = formatDate(list)
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
                item.date = DateFormat().getFormat(item.date)
                itemResponse = item
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun post(obj: AudioRequest) {
        loading = true
        viewModelScope.launch {
            try {
                val item = apiService.post(AuthTokenService().getAuthToken(), obj)
                item.date = DateFormat().getFormat(item.date)
                itemResponse = item
                loading = false
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun put(obj: AudioRequest) {
        viewModelScope.launch {
            try {
                val item = apiService.put(AuthTokenService().getAuthToken(), obj)
                item.date = DateFormat().getFormat(item.date)
                itemResponse = item
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

    private fun formatDate(list: List<AudioResponse>): List<AudioResponse> {
        val dateFormat = DateFormat()
        list.forEachIndexed { i, obj ->
            list[i].date = dateFormat.getFormat(obj.date)
        }
        return list
    }
}