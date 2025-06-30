package br.com.systechbrasil.robovoz.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.shimmer


@Composable
fun ShimmerOnParent() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp).padding(bottom = 10.dp),
    ) {
        Row(
            modifier = Modifier.padding()
                .fillMaxSize()
                .padding(16.dp)
                .shimmer(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .background(Color.LightGray),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .background(Color.LightGray),
                )
                Box(
                    modifier = Modifier
                        .size(120.dp, 20.dp)
                        .background(Color.LightGray),
                )
            }
        }
    }
//    Text(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 8.dp),
//        text = "Shimmer on Parent",
//        textAlign = TextAlign.Center,
//    )
}

@Composable
fun ShimmerOnChildren() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(112.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp, 80.dp)
                    .shimmer()
                    .background(Color.LightGray),
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(24.dp)
                        .shimmer()
                        .background(Color.LightGray),
                )
                Box(
                    modifier = Modifier
                        .size(120.dp, 20.dp)
                        .shimmer()
                        .background(Color.LightGray),
                )
            }
        }
    }
//    Text(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 8.dp),
//        text = "Shimmer on Child Views",
//        textAlign = TextAlign.Center,
//    )
}
