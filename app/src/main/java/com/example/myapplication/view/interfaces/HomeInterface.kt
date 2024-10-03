package com.example.myapplication.view.interfaces

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.model.ListModal
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.model.response.SchedulingResponse
import com.example.myapplication.viewModel.PlaylistViewModel
import com.example.myapplication.viewModel.UsersViewModel
import java.text.SimpleDateFormat
import java.util.*

class HomeInterface {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    @SuppressLint("NotConstructor")
    @Composable
    fun List(loading: Boolean, movieList: List<SchedulingResponse>, context: Context) {

        if (loading)
            LoadingPage("Carregando Agendas")
        else {
            val focusManager = LocalFocusManager.current
            var showDatePickerDialog by remember {
                mutableStateOf(false)
            }
            val datePickerState = rememberDatePickerState()
            var selectedDate by remember {
                mutableStateOf("")
            }
            selectedDate = convertMillisToDate()
            if (showDatePickerDialog) {
                DatePickerDialog(
                    onDismissRequest = { showDatePickerDialog = false },
                    confirmButton = {
                        Button(
                            onClick = {
                                datePickerState
                                    .selectedDateMillis?.let { millis ->
                                        selectedDate = millis.toBrazilianDateFormat()
                                    }
                                showDatePickerDialog = false
                            }) {
                            Text(text = "Escolher data")
                        }
                    }) {
                    DatePicker(state = datePickerState)
                }
            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.white))
                    .wrapContentSize(Alignment.TopStart)
                    .padding(20.dp)
            ) {

                Row {
                    Text(
                        text = "Agendamentos",
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth(.7f),
                        fontSize = 32.sp
                    )
                    Text(
                        text = "Vox Maestro",
                        fontWeight = FontWeight.Thin,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.padding(0.dp, 15.dp),
                        fontSize = 16.sp
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(0.dp)) {
                    Text(
                        text = selectedDate,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Right,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .weight(1f)
                    )

                    Button(
                        onClick = {
                            showDatePickerDialog = true
                            focusManager.clearFocus(force = true)
                        },
                        shape = CircleShape,
                        modifier = Modifier.size(40.dp),
                        contentPadding = PaddingValues(1.dp)
                    ) {
                        Icon(
                            painterResource(id = R.drawable.baseline_library_music_24),
                            contentDescription = "Favorite",
                            modifier = Modifier.size(20.dp)
                        )
                    }


                }

                var selectedIndex by remember { mutableStateOf(-1) }
                LazyColumn {
                    itemsIndexed(items = movieList) { index, item ->
                        HomeListItem(item = item, index, selectedIndex, context) { i ->
                            selectedIndex = i
                        }
                    }
                }
            }
        }
    }

    fun convertMillisToDate(): String {
        val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
        return formatter.format(Date())
    }


    fun Long.toBrazilianDateFormat(
        pattern: String = "dd/MM/yyyy"
    ): String {
        val date = Date(this)
        val formatter = SimpleDateFormat(
            pattern, Locale("pt-br")
        ).apply {
            timeZone = TimeZone.getTimeZone("GMT")
        }
        return formatter.format(date)
    }
}