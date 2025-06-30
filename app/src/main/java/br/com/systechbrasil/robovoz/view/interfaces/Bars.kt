package br.com.systechbrasil.robovoz.view.interfaces

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.view.theme.NavigationItem
import myColor

class Bars {
    @Composable
    fun topBar() {
        TopAppBar(
            title = { Image(modifier = Modifier.width(220.dp), painter = painterResource(R.drawable.robovoz_logo_horizontal), contentDescription = "") },
            backgroundColor = myColor.blueDefault,// Color(R.color.primary),
            contentColor = Color.White

        )
    }
}