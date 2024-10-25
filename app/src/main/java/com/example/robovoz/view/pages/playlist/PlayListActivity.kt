package com.example.robovoz.view.pages.playlist

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
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.robovoz.R
import com.example.robovoz.model.request.PlayListRequest
import com.example.robovoz.model.response.AudioResponse
import com.example.robovoz.model.response.PlayListResponse
import com.example.robovoz.util.ValidFileLocal
import com.example.robovoz.view.MainActivity
import com.example.robovoz.view.interfaces.Bars
import com.example.robovoz.view.interfaces.PhotoPicker
import com.example.robovoz.view.theme.JetPackBottomNavigationTheme
import com.example.robovoz.view.pages.audios.audioListItem
import com.example.robovoz.view.pages.frame.Utils
import com.example.robovoz.viewModel.AudioViewModel
import com.example.robovoz.viewModel.PlaylistViewModel
import com.google.gson.Gson

class PlayListActivity : ComponentActivity() {
    private val viewModel: PlaylistViewModel by viewModels()
    private val viewModelAudios: AudioViewModel by viewModels()
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
                    playListScreen(this@PlayListActivity, obj) { p1: String, p2: String, p3: String, p4:Boolean ->
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
fun playListScreen(context: Context, obj: PlayListResponse, clickListener: (String, String, String, Boolean) -> Unit) {
    var title by remember { mutableStateOf(obj.name) }
    var description by remember { mutableStateOf(obj.description) }

    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {
                Column{
                    Utils().getSubTitle("Play List")
                    Text(
                        text = "Criado em: ${obj.date}",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.LightGray, textAlign = TextAlign.Right
                    )

                    PhotoPicker().photoPickerScreen()
                    OutlinedTextField(
                        value = title,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Título") },
                        onValueChange = {
                            title = it
                        },
                    )

                    OutlinedTextField(
                        value = description,
                        modifier = Modifier.fillMaxWidth().heightIn(min = 100.dp),
                        label = { Text(text = "Descrição") },
                        onValueChange = {
                            description = it
                        }
                    )

                    Row {
                        Button(
                            onClick = {
                                clickListener(title, description, "Image", false)
                            },

                            enabled = (title.isNotEmpty() && description.isNotEmpty()),
                            modifier = Modifier
                                .size(180.dp, 60.dp)
                                .padding(10.dp)
                                .background(color = Color(R.color.primary)),
                              //  .align(alignment = Alignment.End),
                            contentPadding = PaddingValues(1.dp)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_library_music_branco),
                                contentDescription = "Favorite",
                                modifier = Modifier.size(20.dp).padding(10.dp).background(color = Color.White)
                            )
                            androidx.compose.material.Text(text = "Salvar...")
                        }

                        Button(
                            onClick = {
                                clickListener(title, description, "Image", true)
                            },

                            enabled = obj.id != 0,
                            modifier = Modifier
                                .size(180.dp, 60.dp)
                                .padding(10.dp)
                                .background(color = Color(R.color.primary)),
                             //   .align(alignment = Alignment.End),
                            contentPadding = PaddingValues(1.dp)
                        ) {
                            Icon(
                                painterResource(id = R.drawable.baseline_library_music_branco),
                                contentDescription = "Favorite",
                                modifier = Modifier.size(20.dp).padding(10.dp).background(color = Color.White)
                            )
                            androidx.compose.material.Text(text = "Deletar...")
                        }
                    }

                    Row {
                        var x = ValidFileLocal(context, "")
                        listAudios(obj.audios, x)
                    }
                }

            }
        },
        backgroundColor = colorResource(R.color.white)
    )
}

@Composable
fun listAudios(itemListResponse: List<AudioResponse>, validFileLocal: ValidFileLocal){
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