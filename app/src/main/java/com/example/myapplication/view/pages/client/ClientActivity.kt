package com.example.myapplication.view.pages.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.myapplication.model.request.ClientRequest
import com.example.myapplication.view.theme.MyLoginApplicationTheme
import com.example.myapplication.viewModel.ClientViewModel

class ClientActivity : ComponentActivity() {
    private val viewModel: ClientViewModel by viewModels()
    private val clientForm: ClientForm = ClientForm()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent{
            MyLoginApplicationTheme {
                clientForm.form(
                    viewModel,
                    { p1: ClientRequest -> execute(p1) },
                    { onBackPressed()}
                )
                clientForm.openAlert(description =  viewModel.desc, open = viewModel.openDialog) { onBackPressed() }
            }
        }
    }

    private fun execute(item: ClientRequest){
        viewModel.post(item)
        viewModel.result.observe(this) { data ->
            if(data.status == 1 || data.status == 2) {
                viewModel.setDialog(data.message)
            }
        }
    }
}