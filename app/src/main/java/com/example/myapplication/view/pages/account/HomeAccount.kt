package com.example.myapplication.view.pages.account

import myColor
import android.content.Context
import android.content.Intent
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
        val isChecked = remember { mutableStateOf(false) }

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

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(
                                modifier = Modifier
                                    .weight(0.1f)
                                    .padding(0.dp, 0.dp, 5.dp, 10.dp)
                            ) {
                                Checkbox(
                                    checked = isChecked.value,
                                    onCheckedChange = {
                                        isChecked.value = it
                                        LocalData(context).setLoop(isChecked.value)
                                    },
                                    modifier = Modifier.padding(8.dp),
                                    enabled = true,
                                    colors = CheckboxDefaults.colors(
                                        checkedColor = myColor.green,
                                        uncheckedColor = myColor.red,
                                        checkmarkColor = Color.White
                                    ),

                                    interactionSource = remember { MutableInteractionSource() }
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .weight(0.9f)
                                    .padding(0.dp, 0.dp, 5.dp, 10.dp)
                            ) {
                                Text(
                                    text = if (isChecked.value) "Executar playlist em Loop" else "Não executar playlist em Loop",
                                    modifier = Modifier.padding(top = 22.dp)
                                )
                            }
                        }

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