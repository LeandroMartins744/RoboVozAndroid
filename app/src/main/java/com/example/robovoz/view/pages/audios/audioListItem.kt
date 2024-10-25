package com.example.robovoz.view.pages.audios

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
import com.example.robovoz.R
import com.example.robovoz.model.response.AudioResponse
import com.example.robovoz.util.ValidFileLocal


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

            IconButton(onClick = { onMusic(item.audioFile, index) }) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier.size(80.dp)
                )
            }
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
            IconButton(onClick = { onMusic(item.audioFile, index) }) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier.size(50.dp)
                )
            }

        }
    }
}

