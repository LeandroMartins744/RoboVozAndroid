package com.example.robovoz.view.interfaces

import androidx.compose.material.*
import androidx.compose.runtime.Composable

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