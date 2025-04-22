package com.example.myapplication.view.pages.playlist

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.util.DateFormat
import myColor


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun playListItem(item: PlayListResponse, index: Int, selectedIndex: Int, onClick: (Int) -> Unit){
    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background

    Card(
        onClick = { onClick(index) },
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        elevation = 0.dp
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
                    model = R.drawable.img,
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
                        text = DateFormat().getDate(item.date),
                        modifier = Modifier.padding(4.dp),
                        color = Color.LightGray, textAlign = TextAlign.Right
                    )
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                HorizontalDivider(thickness = 0.5.dp)
            }
        }
    }
}
