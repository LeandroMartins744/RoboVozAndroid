package com.example.myapplication.view.theme.frame

import android.R
import android.content.Context
import android.widget.ImageView
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


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PlayListItem(item: PlayListResponse, index: Int, selectedIndex: Int, context: Context, onClick: (Int) -> Unit){
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

                AsyncImage(
                    model = item.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
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
}

