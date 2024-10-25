package br.com.robovoz.util

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import br.com.robovoz.R

class VoicesCloud(val context: Context, var name: String) {
    var mMedia: MediaPlayer = MediaPlayer.create(context, R.raw.audio_test)

    fun getMediaStop() = mMedia.stop()

    fun getMedia()  {
        mMedia.stop()
        mMedia = MediaPlayer.create(context, Uri.parse(name))
    }
}