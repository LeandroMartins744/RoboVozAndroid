package br.com.systechbrasil.robovoz.view.pages.playlist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.model.response.PlayListResponse
import br.com.systechbrasil.robovoz.view.interfaces.NotItemList
import br.com.systechbrasil.robovoz.view.interfaces.TitlePage
import br.com.systechbrasil.robovoz.view.interfaces.loadingPage


class PlaylistHome_old {
    @SuppressLint("NotConstructor")
    @Composable
    fun list(loading: Boolean, movieList: List<PlayListResponse>, onClick: (PlayListResponse) -> Unit) {

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
                            playListItem(
                                item = item,
                                index,
                                selectedIndex
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