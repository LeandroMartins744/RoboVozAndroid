package com.example.myapplication.view.pages.client

import myColor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.model.request.ClientRequest
import com.example.myapplication.util.DateFormat
import com.example.myapplication.util.mask.CepVisualTransformation
import com.example.myapplication.util.mask.DateVisualTransformation
import com.example.myapplication.util.mask.PhoneVisualTransformation
import com.example.myapplication.view.interfaces.*
import com.example.myapplication.viewModel.ClientViewModel

class ClientForm {
    @Composable
    fun form(viewModel: ClientViewModel, clickListener: (ClientRequest) -> Unit, onBack: () -> Unit) {
        var client by remember { mutableStateOf(ClientRequest()) }

        Scaffold(
            content = {
                Box(modifier = Modifier.padding(10.dp).fillMaxSize()) {
                    Column (Modifier.verticalScroll(rememberScrollState())){
                        TitlePage().setTitle("Cadastro", "")

                        myField(
                            value = client.name,
                            modifier = Modifier.fillMaxWidth(),
                            label = "Nome",
                            onChange = { data -> client = client.copy(name = data) },
                            enable = true
                        )

                        myField(
                            value = client.email,
                            modifier = Modifier.fillMaxWidth(),
                            label = "Email",
                            onChange = { data -> client = client.copy(email = data) },
                            enable = true
                        )

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.weight(1f).padding(0.dp, 0.dp, 5.dp, 10.dp)) {
                                myFieldNumber(
                                    value = client.phone,
                                    modifier = Modifier.fillMaxWidth(),
                                    label = "Telefone",
                                    //visualTransformation = PhoneVisualTransformation(),
                                    onChange = { data ->
                                        if(data.length < 12)
                                            client = client.copy(phone = data)
                                    },
                                    enable = true
                                )
                            }

                            Column(modifier = Modifier.weight(1f).padding(0.dp, 0.dp, 5.dp, 10.dp)) {
                                myFieldNumber(
                                    value = client.birthDay,
                                    modifier = Modifier.fillMaxWidth(),
                                    placeholder = "dd/MM/yyyy",
                                    label = "Aniversário",
                                    //visualTransformation = DateVisualTransformation(),
                                    onChange = { data ->
                                        if(data.length < 9)
                                            client = client.copy(birthDay = data)
                                    },
                                    enable = true
                                )
                            }
                        }


                        myFieldMask(
                            value = client.cep,
                            modifier = Modifier.fillMaxWidth(),
                            label = "Cep",
                            visualTransformation = CepVisualTransformation(),
                            onChange = { data ->
                                if (data.length < 9)
                                    client = client.copy(cep = data)
                            },
                            enable = true
                        )
                        myField(
                            value = client.street,
                            modifier = Modifier.fillMaxWidth(),
                            label = "Rua",
                            onChange = { data -> client = client.copy(street = data) },
                            enable = true
                        )

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.weight(1f).padding(0.dp, 0.dp, 5.dp, 10.dp)) {
                                myField(
                                    value = client.number,
                                    modifier = Modifier.fillMaxWidth(),
                                    label = "Numero",
                                    onChange = { data -> client = client.copy(number = data) },
                                    enable = true
                                )
                            }
                            Column(modifier = Modifier.weight(1f).padding(0.dp, 0.dp, 5.dp, 10.dp)) {
                                myField(
                                    value = client.complement,
                                    modifier = Modifier.fillMaxWidth(),
                                    label = "Complemento",
                                    onChange = { data -> client = client.copy(complement = data) },
                                    enable = true
                                )
                            }
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.weight(1f).padding(0.dp, 0.dp, 5.dp, 10.dp)) {
                                myField(
                                    value = client.neighborhood,
                                    modifier = Modifier.fillMaxWidth(),
                                    label = "Bairro",
                                    onChange = { data -> client = client.copy(neighborhood = data) },
                                    enable = true
                                )
                            }
                            Column(modifier = Modifier.weight(1f).padding(0.dp, 0.dp, 5.dp, 10.dp)) {
                                myField(
                                    value = client.city,
                                    modifier = Modifier.fillMaxWidth(),
                                    label = "Cidade",
                                    onChange = { data -> client = client.copy(city = data) },
                                    enable = true
                                )
                            }
                        }
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Column(modifier = Modifier.weight(1f).padding(0.dp, 0.dp, 5.dp, 10.dp)) {
                                dropDownList(DateFormat().getStates()){ client.state = it}
                            }
                            Column(modifier = Modifier.weight(1f).padding(0.dp, 0.dp, 5.dp, 10.dp)) {
                                myField(
                                    value = client.company,
                                    modifier = Modifier.fillMaxWidth(),
                                    label = "Empresa",
                                    onChange = { data -> client = client.copy(company = data) },
                                    enable = true
                                )
                            }
                        }

                        myButton("Cadastrar", enable = viewModel.loading) {
                            clickListener(client)
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        myButton(text = "Cancelar", color = myColor.orange) {
                            onBack()
                        }
                    }
                }
            },
            backgroundColor = colorResource(R.color.white)
        )
    }

    @Composable
    fun openAlert(description: String = "", open: Boolean, onCLick: () -> Unit){
        val openDialog = remember { mutableStateOf(open) }
        val desc = remember { mutableStateOf("") }
        openDialog.value = open
        desc.value = description
        Alert().message("Atenção", description, openDialog) {
            onCLick()
        }
    }


}