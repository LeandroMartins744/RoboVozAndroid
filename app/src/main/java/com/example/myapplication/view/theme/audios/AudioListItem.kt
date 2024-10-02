package com.example.myapplication.view.theme.audios

import android.content.Context
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.model.response.AudioResponse
import com.example.myapplication.model.response.PlayListResponse


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AudioListItem(item: AudioResponse, index: Int, selectedIndex: Int, context: Context, onClick: (Int) -> Unit) {
    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background

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
            val mMediaPlayer = MediaPlayer.create(context, R.raw.audio_test)
            IconButton(onClick = { mMediaPlayer.start() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_play_circle_outline_24),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }
            IconButton(onClick = { mMediaPlayer.stop() }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_pause),
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

