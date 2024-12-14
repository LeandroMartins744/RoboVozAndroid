package com.example.myapplication.view.pages.audios

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.request.AudioRequest
import com.example.myapplication.model.response.VoicesResponse
import com.example.myapplication.util.LocalData
import com.example.myapplication.util.ValidFileLocal
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.interfaces.Bars
import com.example.myapplication.view.interfaces.TitlePage
import com.example.myapplication.view.interfaces.myButton
import com.example.myapplication.view.pages.home.bottomSheet
import com.example.myapplication.view.pages.playlist.PlayLisFragment
import com.example.myapplication.view.pages.playlist.bottomSheetVoice
import com.example.myapplication.view.theme.JetPackBottomNavigationTheme
import com.example.myapplication.viewModel.AudioViewModel
import com.example.myapplication.viewModel.VoicesViewModel
import myColor
import kotlin.math.truncate

class AudioActivity : ComponentActivity() {
    private val viewModelAudio: AudioViewModel by viewModels()
    private val viewModelVoices: VoicesViewModel by viewModels()
    private var voice: VoicesResponse = VoicesResponse()
    private var playlist: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        playlist = intent.getStringExtra("object").toString().toInt()
        viewModelVoices.get()

        setContent {
            JetPackBottomNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    audioScreen(viewModelVoices.voicesResponse, LocalContext.current){ p1, p2, p3 ->
                        run {
                            createVoice(p1, p2, p3)
                        }
                    }

                    voice = LocalData(this@AudioActivity).getVoice()
                }
            }
        }
    }

    private fun createVoice(name: String, description: String, text: String){
        val item = AudioRequest(name, description, text, voice.id)
        item.playlist = playlist
        viewModelAudio.post(item)
        Toast.makeText(this@AudioActivity, "Ação efetuada com sucesso", Toast.LENGTH_LONG).show()
        val it = Intent(this@AudioActivity, MainActivity::class.java)
        it.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        startActivity(it)
    }
}



@SuppressLint("ResourceAsColor")
@Composable
fun audioScreen(list: List<VoicesResponse>, context: Context, onClick: (String, String, String) -> Unit) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var textVoice by remember { mutableStateOf(TextFieldValue("")) }

    var isClicked: Boolean by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = { Bars().topBar() },
        content = {
            Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {

                Column{
                    TitlePage().setTitle("Criação de Audio")
//                    Text(
//                        text = "Criado em: 08/10/2025 10:35",
//                        modifier = Modifier.fillMaxWidth(),
//                        color = Color.LightGray, textAlign = TextAlign.Right
//                    )
                    Row(modifier = Modifier.height(60.dp).padding(top = 20.dp)) {
                        playerVoice(context){ isClicked = true }

                    }
//                    Row(modifier = Modifier.height(60.dp).padding(top = 60.dp)) {
//                        Row{
//                            Column {
//                                myButton("Selecionar", true, myColor.blue){ isClicked = true }
//                            }
//                        }
//
//                    }
                    OutlinedTextField(
                        value = name,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Digite o Nome do Audio") },
                        onValueChange = {
                            name = it
                        }
                    )

                    OutlinedTextField(
                        value = description,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Digite a Descrição do Audio") },
                        onValueChange = {
                            description = it
                        }
                    )

                    OutlinedTextField(
                        value = textVoice,
                        modifier = Modifier.fillMaxWidth().heightIn(min = 150.dp),
                        label = { Text(text = "Digite o texto para conversão") },
                        onValueChange = {
                            textVoice = it
                        }
                    )
                    Button(
                        onClick = {
                            onClick(name.text, description.text, textVoice.text)
                        },
                        modifier = Modifier
                            .size(180.dp, 60.dp)
                            .padding(10.dp)
                            .background(color = Color(R.color.primary))
                            .align(alignment = Alignment.End),
                        contentPadding = PaddingValues(1.dp)
                    ) {
                        Text(text = "Salvar")
                    }
                }
            }
        },
        backgroundColor = colorResource(R.color.white) // Set background color to avoid the white flashing when you switch between screens
    )

    if (isClicked) {
        val validFileLocal: ValidFileLocal = ValidFileLocal(context, "")
        bottomSheetVoice(list, context, validFileLocal) { it ->
            //playlist = it.name
            //result = it.id
            isClicked = false
        }
    }
}

@Composable
fun playerVoice(context: Context, onClick: () -> Unit){
    val item = LocalData(context).getVoice()
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(5.dp))

        IconButton(onClick = { MediaPlayer.create(context, Uri.parse(item.preview_url)).start() }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_play_circle_outline_24),
                contentDescription = "",
                modifier = Modifier.size(30.dp)
            )
        }
        Column {

            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                androidx.compose.material.Text(
                    text = item.name,
                    color = Color.Black, textAlign = TextAlign.Center
                )
            }
        }

    }

}