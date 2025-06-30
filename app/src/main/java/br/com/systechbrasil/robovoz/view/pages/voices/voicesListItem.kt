package br.com.systechbrasil.robovoz.view.pages.voices

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.model.response.VoicesResponse


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun voicesListItem(
    item: VoicesResponse,
    index: Int,
    selectedIndex: Int,
    default: String,
    onClickPlay: (String, Int) -> Unit,
    onSetDefault: (VoicesResponse) -> Unit
){
    val icon = if (index == selectedIndex) R.drawable.baseline_pause else R.drawable.baseline_play_circle_outline_24
    var strButton = "Selecionar"
    var btnEnable = true
    if(default == item.id){
        strButton = "Selecionado"
        btnEnable = false
    }
    Card(
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        elevation = 0.dp
    )
    {
        Row(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.width(5.dp))

            IconButton(onClick = { onClickPlay(item.preview_url, index) }) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp)
                )
            }
            Column {

                Row(
                    Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.name,
                        color = Color.Black, textAlign = TextAlign.Center
                    )

                    Button(
                        onClick = {
                            onSetDefault(item)
                        },
                        enabled = btnEnable,
                        //shape = RoundedCornerShape(5.dp),
                    ) {
                        Text(strButton)
                    }
                }
            }
        }
        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
            HorizontalDivider(thickness = 0.5.dp)
        }
    }
}

