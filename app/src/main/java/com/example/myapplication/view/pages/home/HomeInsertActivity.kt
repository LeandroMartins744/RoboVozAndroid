package com.example.myapplication.view.pages.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.request.PlayListRequest
import com.example.myapplication.model.request.SchedulingRequest
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.util.ValidFileLocal
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.interfaces.Bars
import com.example.myapplication.view.interfaces.myButton
import com.example.myapplication.view.interfaces.myField
import com.example.myapplication.view.interfaces.myFieldNumber
import com.example.myapplication.view.theme.JetPackBottomNavigationTheme
import com.example.myapplication.view.theme.audios.audioListItem
import com.example.myapplication.view.theme.frame.Utils
import com.example.myapplication.viewModel.AudioViewModel
import com.example.myapplication.viewModel.PlaylistViewModel
import com.example.myapplication.viewModel.SchedulingViewModel
import com.google.gson.Gson
import java.util.*


class HomeInsertActivity : ComponentActivity() {
    private val viewModel: PlaylistViewModel by viewModels()
    private val viewModelSched: SchedulingViewModel by viewModels()
    private var obj: PlayListResponse = PlayListResponse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val informant = intent.getStringExtra("object")

        if (!informant.isNullOrBlank())
            obj = Gson().fromJson(informant, PlayListResponse::class.java)

        setContent {
            JetPackBottomNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    viewModel.get()
                    page()
                }
            }
        }
    }

    @Composable
    private fun page(){
        Scaffold(
            topBar = { Bars().topBar() },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    insert(viewModel.playListResponse) { p1: String, p2: String, p3: String, p4:PlayListResponse ->
                        saveData(p1, p2, p3, p4)
                    }
                }
            },
            backgroundColor = colorResource(R.color.primary) // Set background color to avoid the white flashing when you switch between screens
        )
    }
    private fun saveData(name: String, dia: String, hora: String, play: PlayListResponse){
        val date = Date()
        var plaq = PlayListRequest(play.id, play.name, play.description)

        var sche = SchedulingRequest(name, plaq, date, 1)
        viewModelSched.post(sche)
        Toast.makeText(this, "Cadastro efetuado com sucesso", Toast.LENGTH_LONG).show()
        this.startActivity(Intent(this, MainActivity::class.java))
    }

    private fun deleteItem(){
        viewModel.delete(obj.id)
        Toast.makeText(this, "Remoção efetuada com sucesso", Toast.LENGTH_LONG).show()
        this.startActivity(Intent(this, MainActivity::class.java))
    }
}



@SuppressLint("ResourceAsColor")
@Composable
fun insert(list: List<PlayListResponse>, clickListener: (String, String, String, PlayListResponse) -> Unit) {
    var title by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }
    var hora by remember { mutableStateOf("") }
    var playlist by remember { mutableStateOf("") }
    var result: PlayListResponse = PlayListResponse()

    var isClicked by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {
                Column {
                    Utils().getSubTitle("Agendamento")

                    myField(
                        value = title,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Nome",
                        placeholder = "Digite o nome",
                        onChange = { title = it}
                    )

                    myFieldNumber(
                        value = dia,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Dia",
                        placeholder = "Digite o dia",
                        onChange = { dia = it}
                    )

                    myFieldNumber(
                        value = hora,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Hora",
                        placeholder = "Digite a hora",
                        onChange = { hora = it}
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {
                        myField(
                            value = playlist,
                            modifier = Modifier.fillMaxWidth().weight(.70f).padding(end = 5.dp, bottom = 5.dp),
                            label = playlist,
                            placeholder = "Digite Playlist",
                            enable = false,
                            onChange = {}
                        )
                        Column(
                            modifier = Modifier
                                .weight(0.3f)
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        ) {
                            myButton("Selecionar", true, onClick = { isClicked = true})
                        }
                    }

                   Spacer(modifier = Modifier.height(10.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp, 5.dp, 0.dp)
                        ) {
                            myButton("Cadastrar", true, onClick = {
                                clickListener(title, dia, hora, result)
                            })
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp)
                        ) {
                            myButton("Cancelar", true, color = Color.Gray, onClick = {})
                        }
                    }
                }
            }
        },
        backgroundColor = colorResource(R.color.white) // Set background color to avoid the white flashing when you switch between screens
    )

    if(isClicked){
        bottomSheet(list){ it ->
            playlist = it.name
            result = it
            isClicked = false
        }
    }
}


@Composable
fun listAudios(viewModelAudios: AudioViewModel, validFileLocal: ValidFileLocal){
    Box {
        Spacer(modifier = Modifier.width(5.dp))
        var selectedIndex: Int by remember { mutableStateOf(-1) }

        viewModelAudios.get()

        if (viewModelAudios.loading) {

        } else {
            LazyColumn {
                itemsIndexed(items = viewModelAudios.itemListResponse) { index, item ->
                    audioListItem(
                        item = item,
                        index,
                        selectedIndex,
                        validFileLocal,
                        {
                            selectedIndex = -1
                            validFileLocal.getMediaStop()
                            null
                        }, { p1, p2 ->
                            if (selectedIndex == p2) {
                                validFileLocal.getMediaStop()
                                selectedIndex = -1
                            } else {
                                validFileLocal.name = p1
                                selectedIndex = p2
                                validFileLocal.getMedia()
                                validFileLocal.mMedia?.start()
                                validFileLocal.mMedia?.setOnCompletionListener {
                                    selectedIndex = -1
                                }
                            }
                        })
                }
            }
        }
    }
}