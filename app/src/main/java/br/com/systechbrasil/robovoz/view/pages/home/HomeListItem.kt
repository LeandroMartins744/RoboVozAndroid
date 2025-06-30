package br.com.systechbrasil.robovoz.view.pages.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.systechbrasil.robovoz.model.response.PlayListResponse
import br.com.systechbrasil.robovoz.model.response.SchedulingResponse
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.util.DateFormat
import br.com.systechbrasil.robovoz.view.interfaces.Alert
import br.com.systechbrasil.robovoz.view.interfaces.ButtonNew


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun homeListItem(item: SchedulingResponse, index: Int, selectedIndex: Int, onClick: (Int) -> Unit, onDelete: (Int) -> Unit){
    val backgroundColor =
        if (index == selectedIndex) MaterialTheme.colors.primary else MaterialTheme.colors.background

    val openDialog = remember { mutableStateOf(false) }

    androidx.compose.material.Card(
        onClick = { onClick(index) },
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
        elevation = 0.dp
    )
    {
        Surface(color = backgroundColor) {
            Row(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Spacer(modifier = Modifier.width(5.dp))

                Image(
                    painter = painterResource(id = started(item.started)),
                    modifier = Modifier.height(60.dp).size(40.dp),
                    contentDescription = ""
                )

                Spacer(modifier = Modifier.width(5.dp))

                Column(modifier = Modifier.fillMaxWidth().weight(0.9f)) {
                    Text(
                        text = item.name,
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )

                    Text(
                        text = "Com: ${audiosCount(item.playList)} Audios",
                        modifier = Modifier.padding(4.dp),
                        color = Color.Black, textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Playlist: ${playLIstName(item.playList)} ",
                        modifier = Modifier.padding(4.dp),
                        color = Color.LightGray, textAlign = TextAlign.Right
                    )
                    Text(
                        text = "Criado em: ${DateFormat().getFormat(item.date)}",
                        modifier = Modifier.padding(4.dp),
                        color = Color.LightGray, textAlign = TextAlign.Right
                    )
                }
                Column(modifier = Modifier.fillMaxWidth().weight(0.1f)) {
                    ButtonNew().deleteSmallButton{
                        openDialog.value = true
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
                HorizontalDivider(thickness = 0.5.dp)
            }
        }

        Alert().confirmation(
            "Atenção",
            "Tem certeza que quer remover o item ?",
            openDialog,
            onConfirmation = {
                onDelete(item.id)
                openDialog.value = false
            },
            onCancel = { openDialog.value = false })
    }
}
fun started(obj:Boolean) = if(obj) R.drawable.baseline_schedule_ok_24 else R.drawable.baseline_schedule_24
fun audiosCount(obj: PlayListResponse?) =  obj?.audios?.count() ?: 0
fun playLIstName(obj: PlayListResponse?) = obj?.name ?: "Sem Playlist+"
