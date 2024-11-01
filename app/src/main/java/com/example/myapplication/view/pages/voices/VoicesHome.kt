package com.example.myapplication.view.pages.voices

import android.annotation.SuppressLint
import android.content.Context
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
import com.example.myapplication.model.response.VoicesResponse
import com.example.myapplication.util.LocalData
import com.example.myapplication.util.VoicesCloud
import com.example.myapplication.view.interfaces.NotItemList
import com.example.myapplication.view.interfaces.loadingPage


class VoicesHome {
    @SuppressLint("NotConstructor", "ResourceAsColor")
    @Composable
    fun List(loading: Boolean, movieList: List<VoicesResponse>, context: Context, onClick: (VoicesResponse) -> Unit) {

        if (loading)
            loadingPage("Carregando Vozes")
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
                        text = "Voze's",
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
                Spacer(modifier = Modifier.width(5.dp))

                if(movieList.isNotEmpty()) {
                    val voicesCloud = VoicesCloud(context, movieList[0].preview_url)
                    var selectedIndex by remember { mutableStateOf(-1) }
                    var default by remember { mutableStateOf("") }

                    default = LocalData(context).getVoice().id
                    if (movieList.isEmpty())
                        NotItemList().listClean()
                    else {
                        LazyColumn {
                            itemsIndexed(items = movieList) { index, item ->
                                voicesListItem(
                                    item = item,
                                    index,
                                    selectedIndex,
                                    default,
                                    { p1, p2 ->
                                        voicesCloud.name = p1
                                        selectedIndex = p2
                                        voicesCloud.getMedia()
                                        voicesCloud.mMedia.start()
                                        voicesCloud.mMedia.setOnCompletionListener {
                                            selectedIndex = -1
                                        }
                                    }, { p1 ->
                                        LocalData(context).setVoice(p1)
                                        default = p1.id
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}