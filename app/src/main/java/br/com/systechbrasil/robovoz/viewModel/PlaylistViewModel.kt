package br.com.systechbrasil.robovoz.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.*
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.client.*
import br.com.systechbrasil.robovoz.model.request.PlayListRequest
import br.com.systechbrasil.robovoz.model.response.PlayListResponse
import br.com.systechbrasil.robovoz.util.DateFormat
import br.com.systechbrasil.robovoz.util.LodData

import kotlinx.coroutines.launch

class PlaylistViewModel() : ViewModel() {
    var loading by mutableStateOf(true)
        private set

    //var playlist Dialog
    var openDialogPlayer by mutableStateOf(false)
    var openDialogLoopPlayer by mutableStateOf(false)
    var openDialogIcon by mutableStateOf(R.drawable.baseline_play_circle_outline_24)
    var audioExecutable by mutableStateOf(0)



    var playListResponse: List<PlayListResponse> by mutableStateOf(listOf())
    var playResponse: PlayListResponse by mutableStateOf(PlayListResponse())
    private val apiService = ApiService.getInstance().create(PlaylistEndpoints::class.java)

    val resultData = MutableLiveData<String>()

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
                item.date = item.date//DateFormat().getFormat(item.date)
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
                //item.date = DateFormat().getFormat(item.date)
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
               // item.date = DateFormat().getFormat(item.date)
                playResponse = item
            }
            catch (e: Exception) {
                LodData.setLog(e)
            }
        }
    }

    fun delete(id: Int) {
        resultData.value = "0"
        viewModelScope.launch {
            try {
                apiService.delete(AuthTokenService().getAuthToken(), id)
                resultData.value = "1"
            }
            catch (e: Exception) {
                LodData.setLog(e)
                if(e.message?.contains("403") == true)
                    resultData.value = "2"
                else
                    resultData.value = "1"
            }
        }
    }

    private fun formatDate(list: List<PlayListResponse>): List<PlayListResponse> {
        val dateFormat = DateFormat()
        list.forEachIndexed { i, obj ->
            //list[i].date = dateFormat.getFormat(obj.date)
        }
        return list
    }
}