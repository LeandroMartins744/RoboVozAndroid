package com.example.myapplication.view.interfaces

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

class Alertas {
    @Composable
    fun Alert(name: String,
              showDialog: Boolean,
              onDismiss: () -> Unit) {
        if (showDialog) {
            AlertDialog(
                title = {
                    Text("Title")
                },
                text = {
                    Text(text = name)
                },
                onDismissRequest = onDismiss,
                confirmButton = {
                    TextButton(onClick = onDismiss ) {
                        Text("OK")
                    }
                },
                dismissButton = {}
            )
        }
    }
}