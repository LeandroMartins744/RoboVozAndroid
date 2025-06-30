package br.com.systechbrasil.robovoz.view.interfaces

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.systechbrasil.robovoz.R

class TitlePage {
    @SuppressLint("ResourceAsColor")
    @Composable
    fun setTitle(title: String, subTitle: String = R.string.app_name.toString()){
        Row {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                color = Color(R.color.primary),
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth(.7f),
                fontSize = 32.sp
            )
            Text(
                text = subTitle,
                fontWeight = FontWeight.Thin,
                fontStyle = FontStyle.Italic,
                color = Color.Black,
                textAlign = TextAlign.Right,
                modifier = Modifier.padding(0.dp, 15.dp),
                fontSize = 16.sp
            )
        }
    }
}