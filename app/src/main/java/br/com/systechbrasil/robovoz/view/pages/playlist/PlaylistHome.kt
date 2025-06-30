package br.com.systechbrasil.robovoz.view.pages.playlist

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
import br.com.systechbrasil.robovoz.view.interfaces.ButtonNew
import br.com.systechbrasil.robovoz.view.interfaces.NotItemList
import br.com.systechbrasil.robovoz.view.interfaces.TitlePage
import br.com.systechbrasil.robovoz.view.interfaces.loadingPage


class PlaylistHome {
    @Composable
    fun list(action: PlayLisFragment) {
        if (action.getLoading())
            loadingPage(action.getLoadingText())
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.white))
                    .wrapContentSize(Alignment.TopStart)
                    .padding(20.dp)
            ) {
                TitlePage().setTitle(action.getTitle(), "")

                if (action.getList().isEmpty())
                    NotItemList().listClean()
                else {
                    var selectedIndex by remember { mutableStateOf(-1) }
                    LazyColumn {
                        itemsIndexed(items = action.getList()) { index, item ->
                            playListItem(
                                item = item,
                                index,
                                selectedIndex
                            ) { i ->
                                selectedIndex = i
                                action.onClick(item)
                            }
                        }
                    }
                }
            }
        }

        ButtonNew().actionButton { action.onClickNew() }
    }
}