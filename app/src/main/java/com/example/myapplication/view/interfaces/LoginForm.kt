package com.example.myapplication.view.interfaces

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.myapplication.R
import com.example.myapplication.viewModel.UsersViewModel


class LoginForm {
    @Composable
    fun form(viewModel: UsersViewModel, clickListener: (String, String) -> Unit) {
        var credentials by remember { mutableStateOf(Credentials()) }

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(R.drawable.img),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    modifier = Modifier.height(120.dp),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.height(10.dp))

                LoginField(
                    value = credentials.login,
                    onChange = { data -> credentials = credentials.copy(login = data) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordField(
                    value = credentials.pwd,
                    onChange = { data -> credentials = credentials.copy(pwd = data) },
                    submit = { clickListener(credentials.login, credentials.pwd) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        clickListener(credentials.login, credentials.pwd)
                        viewModel.setValue()
                              },
                    enabled = (credentials.isNotEmpty() && viewModel.isButton),
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(viewModel.textCampo)
                }
            }
        }
    }


    data class Credentials(
        var login: String = "",
        var pwd: String = ""
    ) {
        fun isNotEmpty(): Boolean {
            return login.isNotEmpty() && pwd.isNotEmpty()
        }
    }

    @Composable
    fun LoginField(
        value: String,
        onChange: (String) -> Unit,
        modifier: Modifier = Modifier,
        label: String = "Login",
        placeholder: String = "Digite o Login"
    ) {

        val focusManager = LocalFocusManager.current
        val leadingIcon = @Composable {
            Icon(
                Icons.Default.Person,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
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
            visualTransformation = VisualTransformation.None
        )
    }

    @Composable
    fun PasswordField(
        value: String,
        onChange: (String) -> Unit,
        submit: () -> Unit,
        modifier: Modifier = Modifier,
        label: String = "Senha",
        placeholder: String = "Digite a senha"
    ) {

        var isPasswordVisible by remember { mutableStateOf(false) }

        val leadingIcon = @Composable {
            Icon(
                Icons.Default.Key,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
        val trailingIcon = @Composable {
            IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                Icon(
                    if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                    contentDescription = "",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }


        TextField(
            value = value,
            onValueChange = onChange,
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            keyboardActions = KeyboardActions(
                onDone = { submit() }
            ),
            placeholder = { Text(placeholder) },
            label = { Text(label) },
            singleLine = true,
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )
    }
}