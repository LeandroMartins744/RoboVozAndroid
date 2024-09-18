package com.example.myapplication.view.theme.audios

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.myapplication.view.theme.frame.Utils

@Composable
fun FirstScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Utils().getSubTitle("Texto")
        Text(
            text = "Go to Second Screen",
            color = Color.Black,
            modifier = Modifier.clickable(onClick = {

                // Instrucao para navegar para a segunda screen
                navController.navigate("secondScreen")

            })
        )
    }
}
@Composable
fun SecondScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Utils().getSubTitle("Texto1111")
        Text(
            text = "Go to First Screen",
            color = Color.Black,
            modifier = Modifier.clickable(onClick = {

                // Instrucao para navegar para a primeira screen
                navController.navigate("firstScreen")

            })
        )
    }
}

