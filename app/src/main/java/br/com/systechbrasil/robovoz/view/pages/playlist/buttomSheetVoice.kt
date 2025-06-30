package br.com.systechbrasil.robovoz.view.pages.playlist

import android.content.Context
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import br.com.systechbrasil.robovoz.model.response.PlayListResponse
import br.com.systechbrasil.robovoz.model.response.VoicesResponse
import br.com.systechbrasil.robovoz.util.LocalData
import br.com.systechbrasil.robovoz.util.ValidFileLocal
import br.com.systechbrasil.robovoz.view.pages.voices.voicesListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomSheetVoice(
    list: List<VoicesResponse>,
    context: Context,
    validFileLocal: ValidFileLocal,
    onDismiss: (PlayListResponse) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState()
    var default = LocalData(context).getVoice().id
    var selectedIndex by remember { mutableStateOf(-1) }
    ModalBottomSheet(
        onDismissRequest = {  },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        modifier = Modifier.fillMaxHeight(0.9f)
    ) {
        LazyColumn {
            itemsIndexed(items = list) { index, item ->
                voicesListItem(
                    item = item,
                    index, 0,
                    default,
                    { p1, p2 ->
                        validFileLocal.name = p1
                        selectedIndex = p2
                        validFileLocal.getMedia()
                        validFileLocal.mMedia?.start()
                        validFileLocal.mMedia?.setOnCompletionListener {
                            selectedIndex = -1
                        }
                    }, { p1 ->
                        LocalData(context).setVoice(p1)
                        default = p1.id
                    }
                )
            }
        }
    }
}
