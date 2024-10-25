package br.com.robovoz.view.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import br.com.robovoz.R

class NotItemList {
    @SuppressLint("ResourceAsColor")
    @Composable
    fun listClean(){
        Row {
            Text(
                text = "Nenhum item cadatrado",
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color(R.color.primary),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(.7f),
                fontSize = 16.sp
            )
        }
    }
}