package com.example.myapplication.view.pages.audios

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.response.AudioResponse
import com.example.myapplication.util.ValidFileLocal
import okhttp3.internal.wait
import java.io.File


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun audioListItem(item: AudioResponse, index: Int, selectedIndex: Int, validFileLocal: ValidFileLocal, onClick: (Int) -> Unit, onMusic: (String, Int) -> Unit) {
    val icon =
        if (index == selectedIndex) R.drawable.baseline_pause else R.drawable.baseline_play_circle_outline_24

    Card(
        onClick = {
            onClick(index)
        },
        modifier = Modifier.padding(8.dp),
        elevation = 6.dp
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

            Column {
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
                    text = item.date,
                    modifier = Modifier.padding(4.dp),
                    color = Color.LightGray, textAlign = TextAlign.Right
                )
            }


        }
    }
}

