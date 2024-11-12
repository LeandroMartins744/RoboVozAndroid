package com.example.myapplication.view.pages.account

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.response.UserResponse
import com.example.myapplication.util.LocalData
import com.example.myapplication.view.interfaces.Bars
import com.example.myapplication.view.interfaces.TitlePage
import com.example.myapplication.view.interfaces.myButton
import com.example.myapplication.view.interfaces.myField
import com.example.myapplication.view.pages.audios.AudioActivity
import com.example.myapplication.view.pages.audios.playerVoice
import com.example.myapplication.view.pages.login.LoginActivity

@Composable
fun account(context: Context) {
    var user: UserResponse = LocalData(context).get()
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var description by remember { mutableStateOf(TextFieldValue("")) }
    var textVoice by remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = { Bars().topBar() },
        content = {
            Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {

                Column{
                    TitlePage().setTitle("Meus Dados")

                    myField(
                        value = user.name,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Nome",
                        onChange = {  },
                        enable = false
                    )

                    myField(
                        value = user.email,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Email",
                        onChange = {  },
                        enable = false
                    )

                    myField(
                        value = user.phone,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Telefone",
                        onChange = {  },
                        enable = false
                    )

                    myButton("Sair do Aplicativo"){
                        LocalData(context).clean()
                        context.startActivity(Intent(context, LoginActivity::class.java))
                    }
                }
            }
        },
        backgroundColor = colorResource(R.color.white)
    )
}