package com.example.myapplication.view.pages.audios

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.response.AudioResponse
import com.example.myapplication.util.DateFormat
import com.example.myapplication.util.ValidFileLocal
import com.example.myapplication.view.interfaces.Alert
import com.example.myapplication.view.interfaces.ButtonNew
import okhttp3.internal.wait
import java.io.File


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun audioListItem(item: AudioResponse, index: Int, selectedIndex: Int, download: Boolean,
                  onClick: (Int) -> Unit, onMusic: (String, Int) -> Unit, onDelete: (Int) -> Unit) {

    val icon: Int =
        if(download) R.drawable.baseline_downloading_24
        else if (index == selectedIndex) R.drawable.baseline_pause
        else R.drawable.baseline_play_circle_outline_24
    val openDialog = remember { mutableStateOf(false) }

    Card(
        onClick = { onClick(index) },
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        elevation = 0.dp
    )
    {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(5.dp))

            IconButton(onClick = { onMusic(item.audioFile, index) }) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(5.dp))

            Column(modifier = Modifier.fillMaxWidth().weight(0.9f)) {
                Text(
                    text = item.name,
                    modifier = Modifier.padding(4.dp),
                    color = Color.Black, textAlign = TextAlign.Center
                )

                Text(
                    text = item.description,
                    modifier = Modifier.padding(4.dp),
                    color = Color.Black, textAlign = TextAlign.Center
                )
                Text(
                    text = DateFormat().getDateString(item.date),
                    modifier = Modifier.padding(4.dp),
                    color = Color.LightGray, textAlign = TextAlign.Right
                )
            }

            Column(modifier = Modifier.fillMaxWidth().weight(0.1f)) {
                ButtonNew().deleteSmallButton{
                    openDialog.value = true
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
            HorizontalDivider(thickness = 0.5.dp)
        }
    }
    Alert().confirmation(
        "Atenção",
        "Tem certeza que quer remover o Audio ?",
        openDialog,
        onConfirmation = {
            onDelete(item.id)
            openDialog.value = false
        },
        onCancel = { openDialog.value = false })
}

