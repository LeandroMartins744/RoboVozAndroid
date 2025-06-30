package br.com.systechbrasil.robovoz.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import br.com.systechbrasil.robovoz.client.*
import br.com.systechbrasil.robovoz.model.request.AudioRequest
import br.com.systechbrasil.robovoz.model.response.AudioResponse
import br.com.systechbrasil.robovoz.util.DateFormat
import br.com.systechbrasil.robovoz.util.LodData
import br.com.systechbrasil.robovoz.util.ValidFileLocal
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

class AudioViewModel() : ViewModel() {
    var loading by mutableStateOf(true)
        private set
    var itemListResponse:List<AudioResponse> by mutableStateOf(listOf())
    private var itemResponse: AudioResponse by mutableStateOf(AudioResponse())
    private val apiService = ApiService.getInstance().create(AudioEndpoints::class.java)

    lateinit var validFileLocal: ValidFileLocal

    fun setFile(valid: ValidFileLocal){
        validFileLocal = valid
    }
    fun get() {
        viewModelScope.launch {
            try {
                val list = apiService.get(AuthTokenService().getAuthToken())
                list.setAudio()
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
                loading = false

                validFileLocal.name = item.audioFile
                if(!validFileLocal.existItem())
                    downloadFile()

                item.date = DateFormat().getFormat(item.date)
                itemResponse = item
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun downloadFile(){
        loading = true
        viewModelScope.launch {
            try {
                val item: ResponseBody = apiService.download(AuthTokenService().getAuthToken(), validFileLocal.name)
                validFileLocal.saveFile(item)
                delay(timeMillis = 1000)
                loading = false
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun downloadFilePage(name: String, validFileLocal: ValidFileLocal){
        loading = true
        viewModelScope.launch {
            try {
                val item: ResponseBody = apiService.download(AuthTokenService().getAuthToken(), name)
                validFileLocal.saveFile(item)
                delay(timeMillis = 3000)
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
                //obj.audio = "Novo teste de audio, agora está funcionando, já esta mais que na hora, foi ?"
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

    private suspend fun List<AudioResponse>.setAudio(){
        this.forEach {
            validFileLocal.name = it.audioFile
            if(!validFileLocal.existItem()) {
                downloadFile()
                delay(timeMillis = 1000)
            }
        }
    }


}