package br.com.systechbrasil.robovoz.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import br.com.systechbrasil.robovoz.client.*
import br.com.systechbrasil.robovoz.model.request.AudioRequest
import br.com.systechbrasil.robovoz.model.request.ClientRequest
import br.com.systechbrasil.robovoz.model.request.ClientResultRequest
import br.com.systechbrasil.robovoz.model.response.AudioResponse
import br.com.systechbrasil.robovoz.model.response.ClientResponse
import br.com.systechbrasil.robovoz.model.response.UserResponse
import br.com.systechbrasil.robovoz.util.DateFormat
import br.com.systechbrasil.robovoz.util.LodData
import br.com.systechbrasil.robovoz.util.ValidFileLocal
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