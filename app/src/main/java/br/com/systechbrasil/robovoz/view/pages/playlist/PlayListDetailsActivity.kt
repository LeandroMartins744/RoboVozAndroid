package br.com.systechbrasil.robovoz.view.pages.playlist

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
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.model.response.AudioResponse
import br.com.systechbrasil.robovoz.model.response.PlayListResponse
import br.com.systechbrasil.robovoz.util.LocalData
import br.com.systechbrasil.robovoz.util.ValidFileLocal
import br.com.systechbrasil.robovoz.view.MainActivity
import br.com.systechbrasil.robovoz.view.interfaces.*
import br.com.systechbrasil.robovoz.view.pages.audios.AudioActivity
import br.com.systechbrasil.robovoz.view.theme.JetPackBottomNavigationTheme
import br.com.systechbrasil.robovoz.view.pages.audios.audioListItem
import br.com.systechbrasil.robovoz.viewModel.AudioViewModel
import br.com.systechbrasil.robovoz.viewModel.PlaylistViewModel
import com.google.gson.Gson

class PlayListDetailsActivity : ComponentActivity() {
    private val viewModel: PlaylistViewModel by viewModels()
    private val viewModelAudio: AudioViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actions = PlayLisFragment(this@PlayListDetailsActivity, viewModel, viewModelAudio)

        val informant = intent.getStringExtra("object")
        if (!informant.isNullOrBlank())
            viewModel.playResponse = Gson().fromJson(informant, PlayListResponse::class.java)

        setContent {
            JetPackBottomNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PlaylistDetailsPage(actions).page()


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


