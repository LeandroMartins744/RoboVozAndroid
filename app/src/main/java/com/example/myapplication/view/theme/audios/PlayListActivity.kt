package com.example.myapplication.view.theme.audios

import android.content.Context
import android.content.Intent
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.request.PlayListRequest
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.interfaces.Alertas
import com.example.myapplication.view.theme.JetPackBottomNavigationTheme
import com.example.myapplication.view.theme.frame.Utils
import com.example.myapplication.viewModel.PlaylistViewModel

class PlayListActivity : ComponentActivity() {
    private val viewModel: PlaylistViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackBottomNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlayListScreen(LocalContext.current) { p1: String, p2: String, p3: String ->
                       // saveData(p1, p2)
                        Alertas().AlertDialogSample("titulo", "descri~]ap") { p1 ->

                        }
                    }
                }
            }
        }
    }

    private fun saveData(name: String, description: String){
        viewModel.post(PlayListRequest(name, description))
        //if(!viewModel.loading)
            Toast.makeText(this, "Cadastro efetuado com sucesso", Toast.LENGTH_LONG).show()
        this.startActivity(Intent(this, MainActivity::class.java))
    }
}



@Composable
fun PlayListScreen(context: Context, clickListener: (String, String, String) -> Unit) {
    var dataPlayList by remember { mutableStateOf(PlayListData()) }

    Scaffold(
        //topBar = { TopBar() },
        //bottomBar = { BottomNavigationBar(navController) },
        content = { padding ->
            Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {

                Column{
                    Utils().getSubTitle("Play List")
                    Text(
                        text = "Criado em: 08/10/2025 10:35",
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.LightGray, textAlign = TextAlign.Right
                    )

                    OutlinedTextField(
                        value = dataPlayList.name,
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text(text = "Título") },
                        onValueChange = { obj -> dataPlayList = dataPlayList.copy(name = obj) },
                    )

                    OutlinedTextField(
                        value = dataPlayList.description,
                        modifier = Modifier.fillMaxWidth().heightIn(min = 150.dp),
                        label = { Text(text = "Descrição") },
                        onValueChange = { obj -> dataPlayList = dataPlayList.copy(description = obj) },
                    )
                    Button(
                        onClick = {
                            clickListener(dataPlayList.name, dataPlayList.description, "Image")
                        },

                        enabled = dataPlayList.isNotEmpty(),
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

                    Button(
                        onClick = {
                            onClick()
                        },

                        enabled = dataPlayList.isNotEmpty(),
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
                        androidx.compose.material.Text(text = "Deletar Item...")
                    }


                }
            }
        },
        backgroundColor = colorResource(R.color.white) // Set background color to avoid the white flashing when you switch between screens
    )
}

data class PlayListData(
    var name: String = "",
    var description: String = ""
) {
    fun isNotEmpty(): Boolean {
        return name.isNotEmpty() && description.isNotEmpty()
    }
}