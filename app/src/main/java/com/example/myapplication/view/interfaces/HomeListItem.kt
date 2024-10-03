package com.example.myapplication.view.interfaces

import android.content.Context
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.model.response.SchedulingResponse
import com.example.myapplication.R
import com.example.myapplication.util.DateFormat


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeListItem(item: SchedulingResponse, index: Int, selectedIndex: Int, context: Context, onClick: (Int) -> Unit){
    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background


    androidx.compose.material.Card(
        onClick = { onClick(index)
//            Toast.makeText(
//                context,
//                courseList[index].languageName + " selected..",
//                Toast.LENGTH_SHORT
//            ).show()
        },
        modifier = Modifier.padding(8.dp),
        elevation = 6.dp
    )
    {
        Surface(color = backgroundColor) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(5.dp))

                Image(
                    painter = painterResource(id = started(item.started)),
                    modifier = Modifier.height(60.dp).size(40.dp),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.width(5.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = item.name,
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Com: ${audiosCount(item.playList)} Audios",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Playlist: ${playLIstName(item.playList)} ",
                        modifier = Modifier.padding(4.dp),
                        color = Color.LightGray, textAlign = TextAlign.Right
                    )
                    Text(
                        text = "Criado em: ${DateFormat().getFormat(item.date)}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.LightGray, textAlign = TextAlign.Right
                    )
                }
            }
        }
    }
}
fun started(obj:Boolean) = if(obj) R.drawable.baseline_schedule_ok_24 else R.drawable.baseline_schedule_24
fun audiosCount(obj: PlayListResponse?) =  obj?.audios?.count() ?: 0
fun playLIstName(obj: PlayListResponse?) = obj?.name ?: "Sem Playlist"
