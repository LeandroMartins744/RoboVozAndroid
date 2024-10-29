package com.example.myapplication.view.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

@SuppressLint("ResourceAsColor")
@Composable
fun loadingPage(title: String = "Carregando ....") {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.TopStart)
            .padding(20.dp)
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color(R.color.primary),
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(.7f),
            fontSize = 32.sp
        )
    }
}