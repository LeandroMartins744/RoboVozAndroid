package com.example.myapplication.view.interfaces

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class Alertas {
    @Composable
    fun AlertDialogSample(title: String, desc: String, onClick: (Boolean) -> Unit) {
        MaterialTheme {
            Column {
                val openDialog = remember { mutableStateOf(false)  }

                Button(onClick = {
                    openDialog.value = true
                }) {
                    Text("Click me")
                }

                if (openDialog.value) {

                    AlertDialog(
                        onDismissRequest = {
                            openDialog.value = false
                        },
                        title = {
                            Text(text = title)
                        },
                        text = {
                            Text(desc)
                        },
                        confirmButton = {
                            Button(

                                onClick = {
                                    openDialog.value = false
                                    onClick(true)
                                }) {
                                Text("Confirmar")
                            }
                        },
                        dismissButton = {
                            Button(

                                onClick = {
                                    openDialog.value = false
                                    onClick(false)
                                }) {
                                Text("Cancelar")
                            }
                        }
                    )
                }
            }
        }
    }
}