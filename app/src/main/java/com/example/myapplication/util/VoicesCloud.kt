package com.example.myapplication.util

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.example.myapplication.R
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class VoicesCloud(val context: Context, var name: String) {
    var mMedia: MediaPlayer = MediaPlayer.create(context, R.raw.audio_test)

    fun getMediaStop() = mMedia.stop()

    fun getMedia()  {
        mMedia.stop()
        mMedia = MediaPlayer.create(context, Uri.parse(name))
    }
}