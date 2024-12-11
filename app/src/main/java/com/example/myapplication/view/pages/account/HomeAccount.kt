package com.example.myapplication.view.pages.account

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.response.UserResponse
import com.example.myapplication.util.LocalData
import com.example.myapplication.view.interfaces.*
import com.example.myapplication.view.pages.login.LoginActivity

class HomeAccount {
    @Composable
    fun account(context: Context) {
        val user: UserResponse = LocalData(context).get()
        val openDialog = remember { mutableStateOf(false) }

        Scaffold(
            content = {
                Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {
                    Column {
                        TitlePage().setTitle("Meus Dados")

                        myField(
                            value = user.name,
                            modifier = Modifier.fillMaxWidth(),
                            label = "Nome",
                            onChange = { },
                            enable = false
                        )

                        myField(
                            value = user.email,
                            modifier = Modifier.fillMaxWidth(),
                            label = "Email",
                            onChange = { },
                            enable = false
                        )

                        myField(
                            value = user.phone,
                            modifier = Modifier.fillMaxWidth(),
                            label = "Telefone",
                            onChange = { },
                            enable = false
                        )

                        myButton("Sair do Aplicativo") {
                            openDialog.value = true
                        }
                    }
                }
            },
            backgroundColor = colorResource(R.color.white)
        )

        Alert().confirmation("Atenção", "Tem certeza que quer do Aplicativo ?", openDialog, onConfirmation = {
            LocalData(context).clean()
            context.startActivity(Intent(context, LoginActivity::class.java))
        }, onCancel = { openDialog.value = false })
    }
}