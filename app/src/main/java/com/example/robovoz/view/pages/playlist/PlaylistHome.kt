package com.example.robovoz.view.pages.playlist

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.robovoz.R
import com.example.robovoz.model.response.PlayListResponse
import com.example.robovoz.view.interfaces.NotItemList
import com.example.robovoz.view.interfaces.TitlePage
import com.example.robovoz.view.interfaces.loadingPage


class PlaylistHome {
    @SuppressLint("NotConstructor")
    @Composable
    fun List(loading: Boolean, movieList: List<PlayListResponse>, context: Context, onClick: (PlayListResponse) -> Unit) {

        if (loading)
            loadingPage("Carregando PlayList")
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.white))
                    .wrapContentSize(Alignment.TopStart)
                    .padding(20.dp)
            ) {
                TitlePage().setTitle("PlayList's")

                if (movieList.isEmpty())
                    NotItemList().listClean()
                else {
                    var selectedIndex by remember { mutableStateOf(-1) }
                    LazyColumn {
                        itemsIndexed(items = movieList) { index, item ->
                            PlayListItem(
                                item = item,
                                index,
                                selectedIndex,
                                context
                            ) { i ->
                                selectedIndex = i
                                onClick(item)
                            }
                        }
                    }
                }
            }
        }
    }
}