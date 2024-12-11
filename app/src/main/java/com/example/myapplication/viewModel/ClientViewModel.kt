package com.example.myapplication.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import com.example.myapplication.client.*
import com.example.myapplication.model.request.AudioRequest
import com.example.myapplication.model.request.ClientRequest
import com.example.myapplication.model.request.ClientResultRequest
import com.example.myapplication.model.response.AudioResponse
import com.example.myapplication.model.response.ClientResponse
import com.example.myapplication.model.response.UserResponse
import com.example.myapplication.util.DateFormat
import com.example.myapplication.util.LodData
import com.example.myapplication.util.ValidFileLocal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class ClientViewModel() : ViewModel() {
    var loading by mutableStateOf(true)
        private set
    private var itemResponse: ClientResponse by mutableStateOf(ClientResponse())
    private val apiService = ApiService.getInstance().create(ClientEndpoints::class.java)

    var openDialog by mutableStateOf(false)
        private set
    fun setDialog(text: String){
        this.desc = text
        this.openDialog = true
    }

    var desc by mutableStateOf("")
        private set

//    fun setDesc(){
//        this.desc = "hgfsdd"
//    }

    val result = MutableLiveData<ClientResultRequest>()

    fun get(id: Int) {
        viewModelScope.launch {
            try {
                val item = apiService.get(AuthTokenService().getAuthToken(), id)
                loading = false
                itemResponse = item
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun post(obj: ClientRequest) {
        loading = true
        viewModelScope.launch {
            try {
                val item = apiService.post(AuthTokenService().getAuthToken(), obj)
                result.value = item
                loading = false
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun put(obj: ClientRequest) {
        viewModelScope.launch {
            try {
                val item = apiService.put(AuthTokenService().getAuthToken(), obj)
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


}