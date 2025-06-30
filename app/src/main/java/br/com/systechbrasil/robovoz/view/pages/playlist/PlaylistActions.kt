package br.com.systechbrasil.robovoz.view.pages.playlist

import android.content.Context
import android.content.Intent
import br.com.systechbrasil.robovoz.model.response.PlayListResponse
import br.com.systechbrasil.robovoz.util.ValidFileLocal
import br.com.systechbrasil.robovoz.viewModel.AudioViewModel
import br.com.systechbrasil.robovoz.viewModel.PlaylistViewModel
import com.google.gson.Gson

class PlayLisFragment(
    val context: Context,
    private val viewModelPlaylist: PlaylistViewModel,
    private val viewModelAudio: AudioViewModel){

    private val validFileLocal: ValidFileLocal = ValidFileLocal(context, "")

    fun getTitle() = "PlayList's"
    fun getLoadingText() = "Carregando PlayList"
    fun getLoading() = viewModelPlaylist.loading

    fun getList() = viewModelPlaylist.playListResponse
    fun getPlaylist() = viewModelPlaylist.playResponse
    fun getAudios() = viewModelPlaylist.playResponse.audios

    fun onClick(item: PlayListResponse) {
        val it = Intent(context, PlayListDetailsActivity::class.java)
        it.putExtra("object", Gson().toJson(item))
        context.startActivity(it)
    }
    fun onClickNew(){
        context.startActivity(Intent(context, PlayListActivity::class.java))
    }
    fun onClickDelete(){
        viewModelPlaylist.delete(viewModelPlaylist.playResponse.id)
    }
    fun onClickDeleteAudio(id: Int){
        viewModelAudio.delete(id)
    }

    fun downloadFileMusic(name: String){
        viewModelAudio.downloadFilePage(name, this.validFileLocal)
    }

    fun getValidFileLocal() = validFileLocal
}