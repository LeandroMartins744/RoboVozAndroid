package com.example.myapplication.view.pages.playlist

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.request.PlayListRequest
import com.example.myapplication.model.response.AudioResponse
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.util.LocalData
import com.example.myapplication.util.ValidFileLocal
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.interfaces.Bars
import com.example.myapplication.view.interfaces.ButtonNew
import com.example.myapplication.view.interfaces.NotItemList
import com.example.myapplication.view.pages.audios.AudioActivity
import com.example.myapplication.view.theme.JetPackBottomNavigationTheme
import com.example.myapplication.view.pages.audios.audioListItem
import com.example.myapplication.viewModel.PlaylistViewModel
import com.google.gson.Gson

class PlayListDetailsActivity : ComponentActivity() {
    private val viewModel: PlaylistViewModel by viewModels()
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
                    page()
                    ButtonNew().actionButton {
                        if(LocalData(this@PlayListDetailsActivity).getVoice().id == "")
                            Toast.makeText(this@PlayListDetailsActivity, "Para cadastrar Audios, você precisa selecionar a Voz Default", Toast.LENGTH_SHORT).show()
                        else
                            this@PlayListDetailsActivity.startActivity(Intent(this@PlayListDetailsActivity, AudioActivity::class.java))
                    }
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
                    details(this@PlayListDetailsActivity, obj) { p1: String, p2: String, p3: String, p4:Boolean ->
                        if(p4)
                            deleteItem()
                        else
                            saveData(p1, p2)
                    }


                }
            },
            backgroundColor = colorResource(R.color.primary) // Set background color to avoid the white flashing when you switch between screens
        )
    }
    private fun saveData(name: String, description: String){
        if(obj.id == 0)
            viewModel.post(PlayListRequest(name, description))
        else
            viewModel.put(PlayListRequest(obj.id, name, description))
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
fun details(context: Context, obj: PlayListResponse, clickListener: (String, String, String, Boolean) -> Unit) {
    var title by remember { mutableStateOf(obj.name) }
    var description by remember { mutableStateOf(obj.description) }
    Scaffold(
        content = {
            Box(
                modifier = Modifier.fillMaxWidth().height(200.dp)
                    .paint(painterResource(R.drawable.img), contentScale = ContentScale.FillBounds)
            )

            Column(modifier = Modifier.height(200.dp).padding(15.dp), horizontalAlignment = Alignment.Start) {
                Text(text = obj.name, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text(text = obj.description, color = Color.White)
                Text(text = "10/01/2024 10:32", color = Color.White, fontStyle = FontStyle.Italic)
            }
            Column(modifier = Modifier.fillMaxSize().padding(0.dp, 150.dp, 0.dp, 0.dp).background(color = Color.White)) {
                Column(modifier = Modifier.fillMaxSize().padding(15.dp), horizontalAlignment = Alignment.Start) {
                    if (obj.audios.isEmpty())
                        NotItemList().listClean()
                    else {
                        var x = ValidFileLocal(context, "")
                        listItensDetails(obj.audios, x)
                    }
                }
            }

        },
        backgroundColor = colorResource(R.color.white)
    )
}

@Composable
fun listItensDetails(itemListResponse: List<AudioResponse>, validFileLocal: ValidFileLocal){
    Box {
        Spacer(modifier = Modifier.width(5.dp))
        var selectedIndex: Int by remember { mutableStateOf(-1) }


        LazyColumn {
            itemsIndexed(items = itemListResponse) { index, item ->
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