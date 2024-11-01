package com.example.myapplication.view.interfaces

import android.annotation.SuppressLint
import android.renderscript.ScriptGroup.Input
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.R


@SuppressLint("ResourceAsColor")
@Composable
fun myField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    icon: ImageVector = Icons.Default.Person,
    spacer: Dp = 15.dp,
    enable: Boolean = true
) {
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            icon,
            contentDescription = "",
            tint = Color(R.color.primary)
        )
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        enabled = enable,
        visualTransformation = VisualTransformation.None
    )

    Spacer(modifier = Modifier.height(spacer))
}

@SuppressLint("ResourceAsColor")
@Composable
fun myFieldNumber(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    icon: ImageVector = Icons.Default.Person,
    spacer: Dp = 15.dp,
    enable: Boolean = true
) {
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            icon,
            contentDescription = "",
            tint = Color(R.color.primary)
        )
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        enabled = enable,
        visualTransformation = VisualTransformation.None
    )

    Spacer(modifier = Modifier.height(spacer))
}