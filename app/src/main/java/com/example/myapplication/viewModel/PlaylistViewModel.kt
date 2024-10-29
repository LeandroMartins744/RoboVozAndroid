package com.example.myapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.myapplication.client.*
import com.example.myapplication.model.request.PlayListRequest
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.util.DateFormat
import com.example.myapplication.util.LodData

import kotlinx.coroutines.launch

class PlaylistViewModel() : ViewModel() {
    var loading by mutableStateOf(true)
        private set
    var playListResponse:List<PlayListResponse> by mutableStateOf(listOf())
    var playResponse: PlayListResponse by mutableStateOf(PlayListResponse())
    private val apiService = ApiService.getInstance().create(PlaylistEndpoints::class.java)

    fun get() {
        viewModelScope.launch {
            try {
                val movieList = apiService.get(AuthTokenService().getAuthToken())
                playListResponse = formatDate(movieList)
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
                playResponse = item
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun post(obj: PlayListRequest) {
        loading = true
        viewModelScope.launch {
            try {
                val item = apiService.post(AuthTokenService().getAuthToken(), obj)
                item.date = DateFormat().getFormat(item.date)
                playResponse = item
                loading = false
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun put(obj: PlayListRequest) {
        viewModelScope.launch {
            try {
                val item = apiService.put(AuthTokenService().getAuthToken(), obj)
                item.date = DateFormat().getFormat(item.date)
                playResponse = item
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

    private fun formatDate(list: List<PlayListResponse>): List<PlayListResponse> {
        val dateFormat = DateFormat()
        list.forEachIndexed { i, obj ->
            list[i].date = dateFormat.getFormat(obj.date)
        }
        return list
    }
}