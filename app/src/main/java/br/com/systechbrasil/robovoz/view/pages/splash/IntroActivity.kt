package br.com.systechbrasil.robovoz.view.pages.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.*
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.view.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import myColor

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

//@Preview()
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



@Preview()
@Composable
fun playListItem(){


    androidx.compose.material.Card(
        modifier = Modifier.padding(bottom = 8.dp, top = 8.dp),
        elevation = 6.dp,
        backgroundColor = myColor.white
    )
    {
        Surface(color = backgroundColor) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(0.dp))

                AsyncImage(
                    model = R.drawable.baseline_schedule_ok_24,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.width(5.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Leandro",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                    Text(
                        text = "item.description",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                    Text(
                        text = "10/10/2025",
                        modifier = Modifier.padding(4.dp),
                        color = Color.LightGray, textAlign = TextAlign.Right
                    )
                }
            }
        }
    }
}