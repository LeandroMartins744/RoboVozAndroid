package com.example.robovoz.view.interfaces

import androidx.compose.foundation.Image
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.robovoz.R

class Bars {
    @Composable
    fun topBar() {
        TopAppBar(
            title = { Image(painter = painterResource(R.drawable.logo), contentDescription = "") },
            backgroundColor = Color(R.color.primary),
            contentColor = Color.White
        )
    }
}