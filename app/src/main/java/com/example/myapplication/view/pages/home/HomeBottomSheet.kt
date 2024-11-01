package com.example.myapplication.view.pages.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.semantics.SemanticsProperties.Text
import androidx.compose.ui.text.input.KeyboardType.Companion.Text
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text
import androidx.compose.material3.*
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.view.theme.playlist.PlayListItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomSheet(list: List<PlayListResponse>, onDismiss: (PlayListResponse) -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = {  },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
        modifier = Modifier.fillMaxHeight(0.9f)
    ) {
        LazyColumn {
            itemsIndexed(items = list) { index, item ->
                PlayListItem(
                    item = item,
                    index,
                    0
                ) { onDismiss(item) }
            }
        }
    }
}
