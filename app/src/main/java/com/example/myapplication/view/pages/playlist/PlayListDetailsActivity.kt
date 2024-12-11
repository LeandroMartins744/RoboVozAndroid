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
import androidx.lifecycle.Observer
import com.example.myapplication.R
import com.example.myapplication.model.response.AudioResponse
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.util.LocalData
import com.example.myapplication.util.ValidFileLocal
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.interfaces.*
import com.example.myapplication.view.pages.audios.AudioActivity
import com.example.myapplication.view.theme.JetPackBottomNavigationTheme
import com.example.myapplication.view.pages.audios.audioListItem
import com.example.myapplication.viewModel.AudioViewModel
import com.example.myapplication.viewModel.PlaylistViewModel
import com.google.gson.Gson

class PlayListDetailsActivity : ComponentActivity() {
    private val viewModel: PlaylistViewModel by viewModels()
    private val viewModelAudio: AudioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val informant = intent.getStringExtra("object")

        if (!informant.isNullOrBlank())
            viewModel.playResponse = Gson().fromJson(informant, PlayListResponse::class.java)

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
                        else {
                            val it = Intent(this@PlayListDetailsActivity, AudioActivity::class.java)
                            it.putExtra("object", viewModel.playResponse.id.toString())
                            this@PlayListDetailsActivity.startActivity(it)
                        }
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
                    details(viewModelAudio, this@PlayListDetailsActivity, viewModel.playResponse, {
                        deleteItem()
                    }, { id ->
                        deleteAudio(id)
                        //viewModel.get(viewModel.playResponse.id)
                        this@PlayListDetailsActivity.startActivity(Intent(this@PlayListDetailsActivity, MainActivity::class.java))
                    })
                }
            },
            backgroundColor = colorResource(R.color.primary) // Set background color to avoid the white flashing when you switch between screens
        )
    }

    private fun deleteItem(){
        viewModel.delete(viewModel.playResponse.id)
        viewModel.resultData.observe(this, Observer { it ->
            if(it == "1"){
                Toast.makeText(this, "Remoção efetuada com sucesso", Toast.LENGTH_LONG).show()
                this.startActivity(Intent(this, MainActivity::class.java))
            }
            if(it == "2"){
                Toast.makeText(this, "Não foi possível deletar o item, pois ele possui agendamento.", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun deleteAudio(id: Int){
        viewModelAudio.delete(id)
        Toast.makeText(this, "Remoção efetuada com sucesso", Toast.LENGTH_LONG).show()
        viewModel.playResponse.audios.forEachIndexed { i, index ->
            if(index.id == id) {
                viewModel.playResponse.audios.remove(index)
                return
            }
        }

       // page()
    }
}


@SuppressLint("ResourceAsColor")
@Composable
fun details(viewModel: AudioViewModel, context: Context, obj: PlayListResponse, onDelete: () -> Unit, onDeleteAudio: (Int) -> Unit) {
    val openDialog = remember { mutableStateOf(false) }

    Scaffold(
        content = {
            Box(
                modifier = Modifier.fillMaxWidth().height(200.dp)
                    .paint(painterResource(R.drawable.img), contentScale = ContentScale.FillBounds)
            )

            Column(modifier = Modifier.height(200.dp).padding(15.dp), horizontalAlignment = Alignment.Start) {

                Row(modifier = Modifier.fillMaxWidth()) {
                    Column(
                        modifier = Modifier
                            .weight(0.9f)
                            .padding(0.dp, 0.dp, 5.dp, 10.dp)
                    ) {
                        Text(text = obj.name, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = obj.description, color = Color.White)
                        Text(text = "10/01/2024 10:32", color = Color.White, fontStyle = FontStyle.Italic)
                    }
                    Column(
                        modifier = Modifier
                            .weight(0.1f)
                            .padding(0.dp, 0.dp, 5.dp, 10.dp), horizontalAlignment = Alignment.End
                    ) {
                        ButtonNew().deleteSmallButton{
                            openDialog.value = true
                        }
                    }
                }

            }
            Column(modifier = Modifier.fillMaxSize().padding(0.dp, 150.dp, 0.dp, 0.dp).background(color = Color.White)) {
                Column(modifier = Modifier.fillMaxSize().padding(15.dp), horizontalAlignment = Alignment.Start) {
                    if (obj.audios.isEmpty())
                        NotItemList().listClean()
                    else {
                        listItensDetails(viewModel, obj.audios, ValidFileLocal(context, ""), onDeleteAudio)
                    }
                }
            }

        },
        backgroundColor = colorResource(R.color.white)
    )

    Alert().confirmation(
        "Atenção",
        "Tem certeza que quer remover o item, todos os audios do playlist será removido ?",
        openDialog,
        onConfirmation = {
            onDelete()
            openDialog.value = false
        },
        onCancel = { openDialog.value = false })
}

@Composable
fun listItensDetails(viewModel: AudioViewModel, itemListResponse: List<AudioResponse>, validFileLocal: ValidFileLocal, onDelete: (Int) -> Unit){
    Box {
        Spacer(modifier = Modifier.width(5.dp))
        var selectedIndex: Int by remember { mutableStateOf(-1) }
        var download: Boolean by remember { mutableStateOf(false) }


        LazyColumn {
            itemsIndexed(items = itemListResponse) { index, item ->
                audioListItem(
                    item = item,
                    index,
                    selectedIndex,
                    download,
                    {
                        selectedIndex = -1
                        validFileLocal.getMediaStop()
                        null
                    }, { p1, p2 ->
                        if (selectedIndex == p2) {
                            validFileLocal.getMediaStop()
                            selectedIndex = -1
                        } else {
                            download = true
                            validFileLocal.name = p1
                            selectedIndex = p2

                            if(!validFileLocal.existItem()) {
                                viewModel.downloadFilePage(p1, validFileLocal)
                            }
                            download = false
                            validFileLocal.getMedia()
                            validFileLocal.mMedia?.start()
                            validFileLocal.mMedia?.setOnCompletionListener {
                                selectedIndex = -1
                            }
                        }
                    }, onDelete)

            }
        }
    }
}