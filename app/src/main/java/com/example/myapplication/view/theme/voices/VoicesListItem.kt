package com.example.myapplication.view.theme.voices

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.model.response.VoicesResponse


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun VoicesListItem(item: VoicesResponse, index: Int, selectedIndex: Int, context: Context, onClick: (String, Int) -> Unit){
    val icon =
        if (index == selectedIndex) R.drawable.baseline_pause else R.drawable.baseline_play_circle_outline_24

    Card(
        onClick = {
           // onClick(index)
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

            //var mMedia = MediaPlayer.create(context, Uri.parse(item.preview_url))
            IconButton(onClick = { onClick(item.preview_url, index) }) {
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


            }


        }
    }
}

