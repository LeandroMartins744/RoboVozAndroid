package br.com.systechbrasil.robovoz.view.pages.audios

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
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.model.request.AudioRequest
import br.com.systechbrasil.robovoz.model.response.VoicesResponse
import br.com.systechbrasil.robovoz.util.LocalData
import br.com.systechbrasil.robovoz.util.ValidFileLocal
import br.com.systechbrasil.robovoz.view.MainActivity
import br.com.systechbrasil.robovoz.view.interfaces.Bars
import br.com.systechbrasil.robovoz.view.interfaces.TitlePage
import br.com.systechbrasil.robovoz.view.interfaces.myButton
import br.com.systechbrasil.robovoz.view.interfaces.myField
import br.com.systechbrasil.robovoz.view.pages.home.bottomSheet
import br.com.systechbrasil.robovoz.view.pages.playlist.PlayLisFragment
import br.com.systechbrasil.robovoz.view.pages.playlist.bottomSheetVoice
import br.com.systechbrasil.robovoz.view.pages.voices.voicesBottomSheet
import br.com.systechbrasil.robovoz.view.theme.JetPackBottomNavigationTheme
import br.com.systechbrasil.robovoz.viewModel.AudioViewModel
import br.com.systechbrasil.robovoz.viewModel.VoicesViewModel
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
                    audioScreen(viewModelVoices.voicesResponse, LocalContext.current){ it ->
                        run {
                            createVoice(it.name, it.description, it.textVoice)
                        }
                    }

                    voice = LocalData(this@AudioActivity).getVoice()
                }
            }
        }
    }

    private fun createVoice(name: String, description: String, text: String){
        val newText = text.replace("\n", " ").replace("\t", " ").replace("\r", " ")
        val item = AudioRequest(name, description, newText, voice.id)
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
fun audioScreen(list: List<VoicesResponse>, context: Context, onClick: (VoiceItem) -> Unit) {
    var audio by remember { mutableStateOf(VoiceItem()) }
    var isClicked: Boolean by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = { Bars().topBar() },
        content = {
            Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {

                Column{
                    TitlePage().setTitle("Criação de Audio", "novo áudio")

                    Row(modifier = Modifier.height(100.dp).padding(top = 20.dp)) {
                        playerVoice(context, list){ isClicked = true }
                    }

                    myField(
                        value = audio.name,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Digite o Nome do Audio",
                        onChange = { data -> audio = audio.copy(name = data) }
                    )

                    myField(
                        value = audio.description,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Digite a Descrição do Audioo",
                        onChange = { data -> audio = audio.copy(description = data) }
                    )

                    myField(
                        value = audio.textVoice,
                        modifier = Modifier.fillMaxWidth().heightIn(min = 150.dp),
                        label = "Digite o texto para conversão",
                        maxLine = 10,
                        onChange = { data -> audio = audio.copy(textVoice = data) }
                    )

                    myButton("Salvar", onClick = { onClick(audio) })
                }
            }
        },
        backgroundColor = colorResource(R.color.white)
    )

    if (isClicked) {
        val validFileLocal = ValidFileLocal(context, "")
        bottomSheetVoice(list, context, validFileLocal) {
            isClicked = false
        }
    }
}

data class VoiceItem(
    var name: String,
    var description: String,
    var textVoice: String,
){
    constructor(): this(
        name = "",
        description = "",
        textVoice = "",
    )
}

@Composable
fun playerVoice(context: Context, list: List<VoicesResponse>, onClick: () -> Unit){
    val item = LocalData(context).getVoice()
    var isClicked by rememberSaveable { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.width(5.dp))

        IconButton(modifier = Modifier.padding(top = 10.dp), onClick = { MediaPlayer.create(context, Uri.parse(item.preview_url)).start() }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_play_circle_outline_24),
                contentDescription = "",
                modifier = Modifier.size(40.dp)
            )
        }

        Column {
            Row(
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically) {
                Text(
                    modifier = Modifier.width(150.dp),
                    text = item.name,
                    color = Color.Black, textAlign = TextAlign.Start
                )
                myButton("Trocar Audio",true, onClick = { isClicked = true })
            }
        }
    }
    if (isClicked) {
        voicesBottomSheet(list) { it ->
            //playlist = it.name
            //result = it.id
            isClicked = false
        }
    }

}