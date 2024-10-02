package com.example.myapplication.view.theme.playlist

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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.request.PlayListRequest
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.theme.JetPackBottomNavigationTheme
import com.example.myapplication.view.theme.frame.Utils
import com.example.myapplication.viewModel.PlaylistViewModel
import com.google.gson.Gson

class PlayListActivity : ComponentActivity() {
    private val viewModel: PlaylistViewModel by viewModels()
    private var obj: PlayListResponse = PlayListResponse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val informant = intent.getStringExtra("object")

        if(!informant.isNullOrBlank())
            obj = Gson().fromJson(informant, PlayListResponse::class.java)

        setContent {
            JetPackBottomNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlayListScreen(obj) { p1: String, p2: String, p3: String, p4:Boolean ->
                        if(p4)
                            deleteItem()
                        else
                            saveData(p1, p2)
                    }
                }
            }
        }
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



@Composable
fun PlayListScreen(obj: PlayListResponse, clickListener: (String, String, String, Boolean) -> Unit) {
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
                        modifier = Modifier.fillMaxWidth().heightIn(min = 150.dp),
                        label = { Text(text = "Descrição") },
                        onValueChange = {
                            description = it }
                    )
                    Button(
                        onClick = {
                            clickListener(title, description, "Image", false)
                        },

                        enabled = (title.isNotEmpty() && description.isNotEmpty()),
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
                            .background(color = MaterialTheme.colorScheme.primary)
                            .align(alignment = Alignment.End),
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
            }
        },
        backgroundColor = colorResource(R.color.white) // Set background color to avoid the white flashing when you switch between screens
    )
}

