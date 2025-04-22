package com.example.myapplication.view.pages.home

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.myapplication.model.response.PlayListResponse
import com.example.myapplication.model.response.SchedulingResponse
import com.example.myapplication.util.DateFormat
import com.example.myapplication.view.interfaces.NotItemList
import com.example.myapplication.view.interfaces.TitlePage
import com.example.myapplication.view.interfaces.loadingPage
import com.example.myapplication.view.interfaces.myButton
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

class HomePage {

    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    @SuppressLint("NotConstructor", "ResourceAsColor")
    @Composable
    fun listSchedule(loading: Boolean, movieList: List<SchedulingResponse>, onClickList: (Int, PlayListResponse) -> Unit, onDelete: (Int) -> Unit) {

        if (loading)
            loadingPage("Carregando Agendas")
        else {
            val focusManager = LocalFocusManager.current
            var showDatePickerDialog by remember {
                mutableStateOf(false)
            }
            val datePickerState = rememberDatePickerState()
            var selectedDate by remember { mutableStateOf("") }
            selectedDate = DateFormat().getDate()
            if (showDatePickerDialog) {
                DatePickerDialog(
                    onDismissRequest = { showDatePickerDialog = false },
                    confirmButton = {
                        Button(
                            onClick = {
                                datePickerState
                                    .selectedDateMillis?.let { millis ->
                                        selectedDate = DateFormat().getDateFormat(millis)
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

                TitlePage().setTitle("Agendamentos", "playList agendados")
                Spacer(modifier = Modifier.width(5.dp))
                Row(modifier = Modifier.fillMaxWidth().padding(0.dp)) {
                    Text(
                        text = selectedDate,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color(R.color.primary),
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
                            painterResource(id = R.drawable.baseline_schedule_24),
                            contentDescription = "Favorite",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                if (movieList.isEmpty())
                    NotItemList().listClean()
                else {
                    var selectedIndex by remember { mutableStateOf(-1) }
                    LazyColumn {
                        itemsIndexed(items = movieList) { index, item ->
                            homeListItem(item = item, index, selectedIndex, onClick = { i ->
                                selectedIndex = i
                                onClickList(index, movieList[i].playList)
                            }, onDelete = { id ->
                                onDelete(id)
                            })
                        }
                    }
                }
            }
        }
    }
}





