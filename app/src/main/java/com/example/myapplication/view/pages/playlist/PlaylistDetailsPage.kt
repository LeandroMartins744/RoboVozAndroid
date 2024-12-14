package com.example.myapplication.view.pages.playlist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.response.AudioResponse
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.util.DateFormat
import com.example.myapplication.util.ValidFileLocal
import com.example.myapplication.view.interfaces.Alert
import com.example.myapplication.view.interfaces.Bars
import com.example.myapplication.view.interfaces.ButtonNew
import com.example.myapplication.view.interfaces.NotItemList
import com.example.myapplication.view.pages.audios.audioListItem
import com.example.myapplication.viewModel.AudioViewModel

class PlaylistDetailsPage(private val action: PlayLisFragment) {

    @Composable
    fun page(){
        Scaffold(
            topBar = { Bars().topBar() },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    details(action.getPlaylist())
                }
            },
            backgroundColor = colorResource(R.color.primary)
        )
    }

    @SuppressLint("ResourceAsColor")
    @Composable
    fun details(obj: PlayListResponse) {
        val openDialog = remember { mutableStateOf(false) }
        Scaffold(
            content = {
                Box(
                    modifier = Modifier.fillMaxWidth().height(200.dp)
                        .paint(painterResource(R.drawable.img), contentScale = ContentScale.FillBounds)
                )
                Column(modifier = Modifier.height(200.dp).padding(15.dp), horizontalAlignment = Alignment.Start) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .weight(0.9f)
                                .padding(0.dp, 0.dp, 5.dp, 10.dp)
                        ) {
                            Text(text = obj.name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text(text = obj.description, color = Color.White)
                            Text(text = DateFormat().getDate(obj.date), color = Color.White, fontStyle = FontStyle.Italic)
                        }
                        Column(
                            modifier = Modifier
                                .weight(0.1f)
                                .padding(0.dp, 0.dp, 5.dp, 10.dp), horizontalAlignment = Alignment.End
                        ) {
                            ButtonNew().deleteSmallButton{
                                openDialog.value = true
                            }
                        }
                    }
                }
                Column(modifier = Modifier.fillMaxSize().padding(0.dp, 150.dp, 0.dp, 0.dp).background(color = Color.White)) {
                    Column(modifier = Modifier.fillMaxSize().padding(15.dp), horizontalAlignment = Alignment.Start) {
                        if (obj.audios.isEmpty())
                            NotItemList().listClean()
                        else {
                            listItemsDetails()
                        }
                    }
                }
            },
            backgroundColor = colorResource(R.color.white)
        )

        Alert().confirmation(
            "Atenção",
            "Tem certeza que quer remover o item, todos os audios do playlist será removido ?",
            openDialog,
            onConfirmation = {
                action.onClickDelete()
                openDialog.value = false
            },
            onCancel = { openDialog.value = false })
    }

    @Composable
    fun listItemsDetails(){
        Box {
            Spacer(modifier = Modifier.width(5.dp))
            var selectedIndex: Int by remember { mutableStateOf(-1) }
            var download: Boolean by remember { mutableStateOf(false) }

            LazyColumn {
                itemsIndexed(items = action.getAudios()) { index, item ->
                    audioListItem(
                        item = item,
                        index,
                        selectedIndex,
                        download,
                        {
                            selectedIndex = -1
                            action.getValidFileLocal().getMediaStop()
                            null
                        }, { p1, p2 ->
                            if (selectedIndex == p2) {
                                action.getValidFileLocal().getMediaStop()
                                selectedIndex = -1
                            } else {
                                download = true
                                action.getValidFileLocal().name = p1
                                selectedIndex = p2

                                if(!action.getValidFileLocal().existItem()) {
                                    action.downloadFileMusic(p1)
                                }
                                download = false
                                action.getValidFileLocal().getMedia()
                                action.getValidFileLocal().mMedia?.start()
                                action.getValidFileLocal().mMedia?.setOnCompletionListener {
                                    selectedIndex = -1
                                }
                            }
                        }, {p1 -> action.onClickDeleteAudio(p1) })

                }
            }
        }
    }
}