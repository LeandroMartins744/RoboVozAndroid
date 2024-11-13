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
import androidx.compose.material.Scaffold
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.request.PlayListRequest
import com.example.myapplication.model.request.SchedulingRequest
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.util.DateFormat
import com.example.myapplication.util.LocalData
import com.example.myapplication.util.ValidFileLocal
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.interfaces.*
import com.example.myapplication.view.theme.JetPackBottomNavigationTheme
import com.example.myapplication.view.pages.audios.audioListItem
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
                    insert(viewModel.playListResponse) {
                        p1: String,
                        p2: Date,
                        p3:Int ->
                        saveData(p1, p2, p3)
                    }
                }
            },
            backgroundColor = colorResource(R.color.primary) // Set background color to avoid the white flashing when you switch between screens
        )
    }


    private fun saveData(name: String, date: Date, play: Int){
        val playList = PlayListRequest(play)
        val dateStr = DateFormat().getFormatUS(date)
        val scheduling = SchedulingRequest(name, playList, dateStr, LocalData(this@HomeInsertActivity).get().id)
        viewModelSched.post(scheduling)
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
fun insert(list: List<PlayListResponse>, clickListener: (String, Date, Int) -> Unit) {
    var title by remember { mutableStateOf("") }
    var day by remember { mutableStateOf("01") }
    var month by remember { mutableStateOf("0") }
    var year by remember { mutableStateOf("2024") }
    var hour by remember { mutableStateOf("01") }
    var min by remember { mutableStateOf("00") }

    var playlist by remember { mutableStateOf("") }
    var result by remember { mutableStateOf(0) }

    var isClicked by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {
                Column {
                    TitlePage().setTitle("Agendamento")

                    myField(
                        value = title,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Nome",
                        placeholder = "Digite o nome",
                        onChange = { title = it }
                    )

                    Text(
                        text = "Selecione o Dia",
                        fontWeight = FontWeight.Thin,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp),
                        fontSize = 16.sp
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp, 5.dp, 10.dp)
                        ) {
                            dropDownList(DateFormat().getDays()){ day = it}
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp, 5.dp, 10.dp)
                        ) {
                            dropDownList(DateFormat().getMonth(), resName = false){ month = it}
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp, 5.dp, 10.dp)
                        ) {
                            dropDownList(DateFormat().getYear()){ year = it}
                        }
                    }

                    Text(
                        text = "Selecione a Hora",
                        fontWeight = FontWeight.Thin,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                        textAlign = TextAlign.Left,
                        modifier = Modifier.fillMaxWidth().padding(0.dp, 0.dp),
                        fontSize = 16.sp
                    )

                    Row(modifier = Modifier.fillMaxWidth()) {

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp, 5.dp, 10.dp)
                        ) {
                            dropDownList(DateFormat().getHour()){ hour = it}
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp, 5.dp, 10.dp)
                        ) {
                            dropDownList(DateFormat().getMinutos()){ min = it}
                        }
                    }

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
                            myButton("Selecionar", true, onClick = { isClicked = true })
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
                                val date = DateFormat().getDate(day, month, year, hour, min)
                                clickListener(title, date, result)
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

    if (isClicked) {
        bottomSheet(list) { it ->
            playlist = it.name
            result = it.id
            isClicked = false
        }
    }
}