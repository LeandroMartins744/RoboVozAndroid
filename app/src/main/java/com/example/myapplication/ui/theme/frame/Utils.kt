package com.example.myapplication.ui.theme.frame

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class Utils {
    @Composable
    fun getSubTitle(title: String) = Row {
        Text(
            text = title,
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

}

