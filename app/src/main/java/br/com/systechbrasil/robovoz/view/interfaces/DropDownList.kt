package br.com.systechbrasil.robovoz.view.interfaces

import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.compose.material3.TextField
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun dropDownList(list: Array<String>, resName: Boolean = true, onClick: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(list[0]) }


    ExposedDropdownMenuBox(
        modifier = Modifier.fillMaxWidth(),
        // .fillMaxWidth(.33f),
        expanded = expanded,
        onExpandedChange = {
            expanded = !expanded
        }
    ) {
        TextField(
            value = selectedText,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.fillMaxWidth().menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            list.forEachIndexed { i, item ->
                DropdownMenuItem(
                    text = { Text(text = item) },
                    onClick = {
                        selectedText = item
                        expanded = false
                        if(resName)
                            onClick(item)
                        else
                            onClick(i.toString())
                    }
                )
            }
        }
    }
}