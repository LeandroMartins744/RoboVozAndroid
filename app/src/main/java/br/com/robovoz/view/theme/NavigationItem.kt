package br.com.robovoz.view.theme

import br.com.robovoz.R

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    data object Home : NavigationItem("home", R.drawable.baseline_home_24, "Home")
    data object Audios : NavigationItem("audio", R.drawable.baseline_library_music_24, "Audios")
    data object Playlist : NavigationItem("playlist", R.drawable.baseline_movie_24, "List")
    data object Voices : NavigationItem("voices", R.drawable.baseline_voice, "Vozes")
    data object Config : NavigationItem("conta", R.drawable.baseline_person_24, "Conta")
}