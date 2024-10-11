package com.example.myapplication.view.interfaces

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.myapplication.R
import com.example.myapplication.view.theme.NavigationItem

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