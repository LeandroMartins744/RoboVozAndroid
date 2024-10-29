package com.example.myapplication.util

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import com.example.myapplication.R
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class ValidFileLocal(val context: Context, var name: String) {
    var mMedia: MediaPlayer? = MediaPlayer.create(context, R.raw.audio_test)

    fun existItem() = File(this.getFile()).exists()
    fun deleteItem() = File(this.getFile()).delete()
    fun getPath() = context.filesDir.absoluteFile.toString()
    fun getFile() = "${this.getPath()}/$name"
    fun getMediaStop() = mMedia?.stop()

    fun getMedia() : MediaPlayer? {
        mMedia?.stop()
//        if(existItem())
//            return MediaPlayer.create(context, Uri.parse(this.getFile()))
        if(existItem()) {
            mMedia = MediaPlayer.create(context, Uri.parse(this.getFile()))
            return mMedia
        }
        return null
    }

    fun saveFile(body: ResponseBody?):String{
        if (body==null)
            return ""
        var input: InputStream? = null
        try {
            val pathFile = getFile()
            input = body.byteStream()

            val fos = FileOutputStream(pathFile)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024) // or other buffer size
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return pathFile
        }catch (e:Exception){
            var x = e
        }
        finally {
            input?.close()
        }
        return ""
    }
}