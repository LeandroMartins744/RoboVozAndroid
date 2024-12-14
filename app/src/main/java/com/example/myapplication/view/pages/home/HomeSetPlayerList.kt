package com.example.myapplication.view.pages.home

import android.content.Context
import com.example.myapplication.R
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.util.LocalData
import com.example.myapplication.util.ValidFileLocal
import com.example.myapplication.viewModel.PlaylistViewModel

class HomeSetPlayerList(val context: Context, val viewModel: PlaylistViewModel) {
    private var validFileLocal: ValidFileLocal = ValidFileLocal(context, "")


    fun setPlayerList(index: Int, playList: PlayListResponse) {
        viewModel.playResponse = playList
        viewModel.openDialogPlayer = true
        viewModel.audioExecutable = 0
        viewModel.openDialogLoopPlayer = LocalData(this.context).getLoop()
    }

    fun onClickPrevious(){
        if(viewModel.audioExecutable > 0){
            viewModel.audioExecutable -= 1
            exeAudio()
        }
    }
    fun onClickPlay(){
        if(viewModel.openDialogIcon == R.drawable.baseline_pause) {
            validFileLocal.getMediaStop()
            viewModel.openDialogIcon = R.drawable.baseline_play_circle_outline_24
        }
        else {
            exeAudio()
        }
    }
    fun onClickNext(){
        if(viewModel.playResponse.audios.count() > viewModel.audioExecutable){
            viewModel.audioExecutable += 1
            exeAudio()
        }
    }


    private fun exeAudio(){
        if(viewModel.playResponse.audios.count() > 0) {
            viewModel.openDialogIcon = R.drawable.baseline_pause

            validFileLocal.name = viewModel.playResponse.audios[viewModel.audioExecutable].audioFile
            validFileLocal.getMedia()
            validFileLocal.mMedia?.start()
            validFileLocal.mMedia?.setOnCompletionListener {
                viewModel.openDialogIcon = R.drawable.baseline_play_circle_outline_24
                Thread.sleep(1000)
                if((viewModel.playResponse.audios.count() - 1) > viewModel.audioExecutable){
                    viewModel.audioExecutable += 1
                    exeAudio()
                }
                else {
                    viewModel.audioExecutable = 0
                    if(viewModel.openDialogLoopPlayer)
                        exeAudio()
                }
            }
        }
    }
}