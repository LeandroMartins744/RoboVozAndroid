package com.example.myapplication.view.pages.splash

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.R

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.airbnb.lottie.compose.*
import com.example.myapplication.view.MainActivity
import kotlinx.coroutines.delay


class SplashActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //installSplashScreen()

        setContent {
            //this.startActivity(Intent(this, MainActivity::class.java))
            SplashScreen()
        }
    }

}
@Composable
fun SplashScreen () {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(myColor.default)
    ) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash))
        val logoAnimationState =
            animateLottieCompositionAsState(composition = composition)

        LottieAnimation(
            modifier = Modifier.padding(top = 100.dp),
            composition = composition,
            iterations = LottieConstants.IterateForever
            //progress = { logoAnimationState.progress }
        )

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 250.dp)
        ) {
        Image(painter = painterResource(R.drawable.logo), contentDescription = "", modifier = Modifier.width(300.dp))
        }
        //logoAnimationState.

        if (logoAnimationState.isAtEnd && logoAnimationState.isPlaying) {
            //navController.navigate(Screen.Home.route)
        }
    }
}

