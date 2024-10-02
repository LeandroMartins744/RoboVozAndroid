package com.example.myapplication.view.theme.playlist

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.view.interfaces.LoadingPage


class Playlist {
    @SuppressLint("NotConstructor")
    @Composable
    fun List(loading: Boolean, movieList: List<PlayListResponse>, context: Context, onClick: (PlayListResponse) -> Unit) {

        if (loading)
            LoadingPage("Carregando PlayList")
        else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.white))
                    .wrapContentSize(Alignment.TopStart)
                    .padding(20.dp)
            ) {

                Row {
                    Text(
                        text = "PlayList's",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(.7f),
                        fontSize = 32.sp
                    )
                    Text(
                        text = "Vox Maestro",
                        fontWeight = FontWeight.Thin,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.padding(0.dp, 15.dp),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(0.dp)) {
                    Button(
                        onClick = {
                            context.startActivity(Intent(context, PlayListActivity::class.java))
                        },
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(1.dp)
                    ) {
                        Icon(
                            painterResource(id = R.drawable.baseline_library_music_24),
                            contentDescription = "Favorite",
                            modifier = Modifier.size(20.dp)
                        )
                    }


                }

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