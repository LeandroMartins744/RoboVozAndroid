package br.com.systechbrasil.robovoz.view.pages.playlist

import myColor
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.model.request.PlayListRequest
import br.com.systechbrasil.robovoz.model.response.PlayListResponse
import br.com.systechbrasil.robovoz.view.MainActivity
import br.com.systechbrasil.robovoz.view.interfaces.Bars
import br.com.systechbrasil.robovoz.view.interfaces.TitlePage
import br.com.systechbrasil.robovoz.view.interfaces.myButton
import br.com.systechbrasil.robovoz.view.theme.JetPackBottomNavigationTheme
import br.com.systechbrasil.robovoz.viewModel.PlaylistViewModel

class PlayListActivity : ComponentActivity() {
    private val viewModel: PlaylistViewModel by viewModels()
    private var obj: PlayListResponse = PlayListResponse()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    playListScreen(obj,
                        { p1: String, p2: String, p3: String, p4:Boolean ->
                            saveData(p1, p2)
                        },{ onBackPressed() })
                }
            },
            backgroundColor = colorResource(R.color.primary)
        )
    }
    private fun saveData(name: String, description: String){
        viewModel.post(PlayListRequest(name, description))

        Toast.makeText(this, "Cadastro efetuado com sucesso", Toast.LENGTH_LONG).show()
        this.startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}



@SuppressLint("ResourceAsColor")
@Composable
fun playListScreen(obj: PlayListResponse, clickListener: (String, String, String, Boolean) -> Unit, onBackCancel: () -> Unit) {
    var title by remember { mutableStateOf(obj.name) }
    var description by remember { mutableStateOf(obj.description) }
    var image by remember { mutableStateOf(obj.image) }


    Scaffold(
        content = { padding ->
            Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {
                Column{
                    TitlePage().setTitle("PlayList")
                    //Seleção de foto
                    //PhotoPicker().photoPickerScreen(tttttt = image)
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

                    Row(modifier = Modifier.fillMaxWidth().padding(top = 10.dp)) {
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp, 0.dp, 5.dp, 0.dp)
                        ) {
                            myButton("Salvar", true, onClick = {
                                clickListener(title, description, "Image", false)
                            })
                        }


                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(0.dp)
                        ) {
                            myButton("Cancelar", true, color = myColor.orange, onClick = {
                                onBackCancel()
                            })
                        }
                    }
                }

            }
        },
        backgroundColor = colorResource(R.color.white)
    )
}