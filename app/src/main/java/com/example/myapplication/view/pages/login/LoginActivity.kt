package com.example.myapplication.view.pages.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.myapplication.util.LocalData
import com.example.myapplication.view.MainActivity
import com.example.myapplication.view.pages.client.ClientActivity
import com.example.myapplication.view.theme.MyLoginApplicationTheme
import com.example.myapplication.viewModel.UsersViewModel

class LoginActivity : ComponentActivity() {
    private val viewModel: UsersViewModel by viewModels()
    private val loginForm: LoginForm = LoginForm()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            MyLoginApplicationTheme {

                loginForm.form(viewModel, { p1: String, p2: String ->
                    execute(p1, p2)
                }, {
                    this.startActivity(Intent(this, ClientActivity::class.java))
                })
            }
        }
    }

    private fun execute(login: String, pass: String){

        if(viewModel.isButton) {
            viewModel.setIsButton(false)
            viewModel.getUsersViewModel(login, pass)
            viewModel.userData.observe(this, Observer { data ->
                viewModel.setIsButton(true)
                if(data.isError)
                    Toast.makeText(this, data.messageError, Toast.LENGTH_SHORT).show()
                if (data.email.isBlank()) {
                    Toast.makeText(this, "Login ou senha inválido!", Toast.LENGTH_SHORT).show()
                    viewModel.setValue()
                }
                else {
                    LocalData(this).set(data)
                    val token = LocalData(this).getTokenFireBase()

                    if (token.token != ""){
                        token.userId = data.id
                        viewModel.getNotifyViewModel(token)
                    }
                    this.startActivity(Intent(this, MainActivity::class.java))
                }
            })
        }
        else {
            Toast.makeText(this, "Carregando dados, aguarde ....", Toast.LENGTH_SHORT).show()
            viewModel.setIsButton(true)
        }
    }
}