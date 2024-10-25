package br.com.robovoz.view.pages.audios

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
import br.com.robovoz.R
import br.com.robovoz.model.response.AudioResponse
import br.com.robovoz.util.ValidFileLocal
import br.com.robovoz.view.interfaces.NotItemList
import br.com.robovoz.view.interfaces.TitlePage
import br.com.robovoz.view.interfaces.loadingPage

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

                TitlePage().setTitle("Audio's")
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