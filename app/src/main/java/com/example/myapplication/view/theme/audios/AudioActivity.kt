package com.example.myapplication.view.theme.audios

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.TextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.*
import com.example.myapplication.R
import com.example.myapplication.view.theme.JetPackBottomNavigationTheme
import com.example.myapplication.view.theme.frame.Utils
import java.util.Locale
import kotlin.math.min

class AudioActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackBottomNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AudioScreen(LocalContext.current)
                }
            }
        }
    }
}



@Composable
fun AudioScreen(context: Context) {
    var text by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        //topBar = { TopBar() },
        //bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {

                Column{
                    Utils().getSubTitle("Criação de Audio")
                    Text(
                        text = "Criado em: 08/10/2025 10:35",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.LightGray, textAlign = TextAlign.Right
                    )

                    OutlinedTextField(
                        value = text,
                        modifier = Modifier.fillMaxWidth().heightIn(min = 150.dp),
                        label = { Text(text = "Digite o texto para conversão") },
                        onValueChange = {
                            text = it
                        }
                    )
                    Button(
                        onClick = {
                            //Toast.makeText(context, "novo click", Toast.LENGTH_SHORT).show()
                            //context.startActivity(Intent(context, AudioActivity::class.java))
                            context.startActivity(Intent(context, Audio2Activity::class.java))
                        },

                        modifier = Modifier
                            .size(180.dp, 60.dp)
                            .padding(10.dp)
                            .background(color = MaterialTheme.colorScheme.primary)
                            .align(alignment = Alignment.End),
                        contentPadding = PaddingValues(1.dp)
                    ) {
                        Icon(
                            painterResource(id = R.drawable.baseline_library_music_branco),
                            contentDescription = "Favorite",
                            modifier = Modifier.size(20.dp).padding(10.dp).background(color = Color.White)
                        )
                        androidx.compose.material.Text(text = "Criar Novo...")
                    }
                }
            }
        },
        backgroundColor = colorResource(R.color.white) // Set background color to avoid the white flashing when you switch between screens
    )
}