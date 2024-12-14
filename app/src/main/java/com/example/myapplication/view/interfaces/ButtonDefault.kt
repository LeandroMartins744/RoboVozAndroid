package com.example.myapplication.view.interfaces

import myColor
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun myButton(text: String, enable: Boolean = true, color: Color = myColor.blue, onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        enabled = enable,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier.fillMaxWidth().height(50.dp)
    ) {
//        Icon(
//            painterResource(id = R.drawable.baseline_library_music_branco),
//            contentDescription = "Favorite",
//            modifier = Modifier.size(20.dp).padding(10.dp).background(color = Color.White)
//        )
        Text(text)
    }
}