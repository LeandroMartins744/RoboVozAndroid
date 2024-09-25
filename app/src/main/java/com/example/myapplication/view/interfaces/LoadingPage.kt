package com.example.myapplication.view.interfaces

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun LoadingPage(title: String = "Carregando ....") {
    Row {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth(.7f),
            fontSize = 32.sp
        )
    }
}