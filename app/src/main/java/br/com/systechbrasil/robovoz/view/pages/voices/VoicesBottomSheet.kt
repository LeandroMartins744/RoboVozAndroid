package br.com.systechbrasil.robovoz.view.pages.voices

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.*
import br.com.systechbrasil.robovoz.model.response.PlayListResponse
import br.com.systechbrasil.robovoz.model.response.VoicesResponse
import br.com.systechbrasil.robovoz.view.pages.playlist.playListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun voicesBottomSheet(list: List<VoicesResponse>, onDismiss: (VoicesResponse) -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

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
                    index,
                    selectedIndex = 0,
                    default = "",
                    onClickPlay = { p1, p2 -> },
                    onSetDefault = { onDismiss(item) }
                )
            }
        }
    }
}
