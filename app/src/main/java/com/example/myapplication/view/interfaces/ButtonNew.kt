package com.example.myapplication.view.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.R


class ButtonNew{
    @Composable
    fun actionButton(hor: Dp = 5.dp, vert: Dp = 10.dp, onClick: () -> Unit){
        Column(
            modifier = Modifier
                .padding(hor, vert)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End,
        ) {
            smallButton(onClick)
        }
    }

    @SuppressLint("ResourceAsColor")
    @Composable
    fun smallButton(onClick: () -> Unit) {
        SmallFloatingActionButton(
            onClick = { onClick() },
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = Color(R.color.button_new)
        ) {
            Icon(Icons.Filled.Add, "Cadastrar novo item")
        }
    }

    @SuppressLint("ResourceAsColor")
    @Composable
    fun smallButton(text: String, onClick: () -> Unit) {
        ExtendedFloatingActionButton(
            onClick = { onClick() },
            icon = { Icon(Icons.Filled.Add, "Extended floating action button.") },
            text = { Text(text = text) },
        )
    }

}