package com.example.myapplication.view.theme.frame

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.ListModal
import com.example.myapplication.view.theme.audios.AudioActivity


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Audios(context: Context) {
    lateinit var courseList: List<ListModal>
    courseList = ArrayList<ListModal>()

    // on below line we are adding data to our list.
    courseList = courseList + ListModal("Audio 01", "Audio Descrição Test 01", R.drawable.baseline_play_circle_outline_24)
    courseList = courseList + ListModal("Audio 02", "Audio Descrição Test 02",R.drawable.baseline_play_circle_outline_24)
    courseList = courseList + ListModal("Audio 03", "Audio Descrição Test 03",R.drawable.baseline_play_circle_outline_24)
    courseList = courseList + ListModal("Audio 04", "Audio Descrição Test 04",R.drawable.baseline_play_circle_outline_24)
    courseList = courseList + ListModal("Audio 05", "Audio Descrição Test 05",R.drawable.baseline_play_circle_outline_24)
    courseList = courseList + ListModal("Audio 06", "Audio Descrição Test 06",R.drawable.baseline_play_circle_outline_24)
    courseList = courseList + ListModal("Audio 07", "Audio Descrição Test 07",R.drawable.baseline_play_circle_outline_24)


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.Center)
    ) {
        Button(
            onClick = {
                //Toast.makeText(context, "novo click", Toast.LENGTH_SHORT).show()
                context.startActivity(Intent(context, AudioActivity::class.java))
            },

            modifier = Modifier
                .size(180.dp, 60.dp)
                .padding(10.dp)
                .background(color = MaterialTheme.colorScheme.primary)
                .align(alignment = Alignment.End),
            contentPadding = PaddingValues(1.dp)
        ) {
            Icon(
                painterResource(id = R.drawable.baseline_library_music_branco),
                contentDescription = "Favorite",
                modifier = Modifier.size(20.dp).padding(10.dp).background(color = Color.White)
            )
            Text(text = "Criar Novo...")
        }
        LazyColumn {
            itemsIndexed(courseList) { index, item ->
                Card(
                    onClick = { Toast.makeText(
                            context,
                            courseList[index].languageName + " selected..",
                            Toast.LENGTH_SHORT
                        ).show()
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
                            Icon(painter = painterResource(id = R.drawable.baseline_play_circle_outline_24), contentDescription = "", modifier = Modifier.size(30.dp))//, Modifier.size(100.dp))
                        }
                        IconButton(onClick = { mMediaPlayer.stop() }) {
                            Icon(painter = painterResource(id = R.drawable.baseline_pause), contentDescription = "", modifier = Modifier.size(30.dp))//, Modifier.size(100.dp))
                        }

                        // on below line we are adding spacer between image and a text
                        Spacer(modifier = Modifier.width(5.dp))

                        Column {
                            Text(
                                text = courseList[index].languageName,
                                modifier = Modifier.padding(4.dp),
                                color = Color.Black, textAlign = TextAlign.Center
                            )

                            Text(
                                text = courseList[index].description,
                                modifier = Modifier.padding(4.dp),
                                color = Color.Black, textAlign = TextAlign.Center
                            )
                            Text(
                                text = "Criado em: 08/10/2025 10:35",
                                modifier = Modifier.padding(4.dp),
                                color = Color.LightGray, textAlign = TextAlign.Right
                            )
                        }

                    }
                }
            }
        }
    }
}