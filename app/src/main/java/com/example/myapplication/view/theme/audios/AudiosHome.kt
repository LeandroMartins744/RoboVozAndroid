package com.example.myapplication.view.theme.audios

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.response.AudioResponse
import com.example.myapplication.util.ValidFileLocal
import com.example.myapplication.view.interfaces.NotItemList
import com.example.myapplication.view.interfaces.loadingPage

class AudioList {
    @SuppressLint("ResourceAsColor")
    @Composable
    fun audios(loading: Boolean, movieList: List<AudioResponse>, validFileLocal: ValidFileLocal, onClick: (AudioResponse) -> Unit) {
        if (loading)
            loadingPage("Carregando Audios")
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.white))
                    .wrapContentSize(Alignment.TopStart)
                    .padding(20.dp)
            ) {

                Row {
                    Text(
                        text = "Audios's",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(R.color.primary),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(.7f),
                        fontSize = 32.sp
                    )
                    Text(
                        text = "Vox Maestro",
                        fontWeight = FontWeight.Thin,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.padding(0.dp, 15.dp),
                        fontSize = 16.sp
                    )
                }

                if (movieList.isEmpty())
                    NotItemList().listClean()
                else {
                    Spacer(modifier = Modifier.width(5.dp))
                    var selectedIndex: Int by remember { mutableStateOf(-1) }

                    LazyColumn {
                        itemsIndexed(items = movieList) { index, item ->
                            audioListItem(
                                item = item,
                                index,
                                selectedIndex,
                                validFileLocal,
                                {
                                    selectedIndex = -1
                                    validFileLocal.getMediaStop()
                                    onClick(item)
                                }, { p1, p2 ->
                                    if (selectedIndex == p2) {
                                        validFileLocal.getMediaStop()
                                        selectedIndex = -1
                                    } else {
                                        validFileLocal.name = p1
                                        selectedIndex = p2
                                        validFileLocal.getMedia()
                                        validFileLocal.mMedia?.start()
                                        validFileLocal.mMedia?.setOnCompletionListener {
                                            selectedIndex = -1
                                        }
                                    }
                                })
                        }
                    }
                }
            }
        }
    }
}