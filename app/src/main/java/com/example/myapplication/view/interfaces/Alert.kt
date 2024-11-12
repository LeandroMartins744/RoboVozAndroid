package com.example.myapplication.view.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material3.AlertDialog
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.myapplication.R

class Alert {

    @Composable
    fun message(title: String,
                description: String,
                openDialog: MutableState<Boolean>,
                buttonName: String = "Ok",
                onDismiss: () -> Unit) {
        if (openDialog.value) {
            AlertDialog(
                title = { Text(title) },
                text = { Text(text = description) },
                onDismissRequest = onDismiss,
                confirmButton = {
                    TextButton(onClick = onDismiss ) {
                        Text(buttonName)
                    }
                },
                dismissButton = {}
            )
        }
    }

    @SuppressLint("ResourceAsColor")
    @Composable
    fun confirmation(
        title: String = "Atenção",
        description: String = "Tem certeza que deseja executar a ação ?",
        open: MutableState<Boolean>,
        onCancel: () -> Unit,
        onConfirmation: () -> Unit) {
        if (open.value) {
            AlertDialog(
                title = {
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(R.color.primary),
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(.7f),
                        fontSize = 26.sp
                    )
                },
                text = { Text(text = description) },
                onDismissRequest = {
                    onCancel()
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onConfirmation()
                        }
                    ) {
                        Text("Sim")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            onCancel()
                        }
                    ) {
                        Text("Não")
                    }
                }
            )
        }
    }

}