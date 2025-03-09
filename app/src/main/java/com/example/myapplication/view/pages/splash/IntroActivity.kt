package com.example.myapplication.view.pages.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.airbnb.lottie.compose.*
import com.example.myapplication.R
import com.example.myapplication.view.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            splashSH()
        }

        lifecycleScope.launch {
            delay(5000)
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            finish()
        }
    }
}

@Preview()
@Composable
fun splashSH(){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.splash),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ){
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

            LottieAnimation(
                modifier = Modifier.padding(top = 100.dp).height(150.dp),

                composition = composition,
                iterations = LottieConstants.IterateForever
            )
        }
    }
}
