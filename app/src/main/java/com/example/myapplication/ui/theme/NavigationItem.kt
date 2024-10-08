package com.example.myapplication.ui.theme

import com.example.myapplication.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    data object Home : NavigationItem("home", R.drawable.baseline_home_24, "Home")
    data object Music : NavigationItem("audio", R.drawable.baseline_library_music_24, "Audios")
    data object Movies : NavigationItem("playlist", R.drawable.baseline_movie_24, "Playlist")
    data object Profile : NavigationItem("conta", R.drawable.baseline_person_24, "Conta")
}