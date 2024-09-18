package com.example.myapplication.view.theme.frame

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ehsanmsz.mszprogressindicator.progressindicator.BallClipRotatePulseProgressIndicator
import com.example.myapplication.model.ListModal
import com.example.myapplication.viewModel.UsersViewModel
import java.text.SimpleDateFormat
import java.util.*


@Composable
fun BaseRow(content: @Composable RowScope.() -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(80.dp, 80.dp)
        )
    }
}

@Composable
fun Home(context: Context, viewModel: UsersViewModel) {

    var courseList: List<ListModal> = ArrayList<ListModal>()

    val users by viewModel.user.observeAsState(null)

    LaunchedEffect(Unit) {
        viewModel.getUsersViewModel()
    }

    Column {
        if (users == null) {
           // com.ehsanmsz.mszprogressindicator.progressindicator.LineScalePartyProgressIndicator()
//            Row {
//                BallPulseProgressIndicator()
//                BallGridPulseProgressIndicator()
//                BallClipRotateProgressIndicator()
//                BallClipRotatePulseProgressIndicator()
//            }
            Text(text = "Loading...")
        } else {
            // Display the list of credit cards
            Text(text = "Carregador")
            Column {
                BaseRow {
//                    BallPulseProgressIndicator()
//                    BallGridPulseProgressIndicator()
//                    BallClipRotateProgressIndicator()
                    BallClipRotatePulseProgressIndicator(
                        modifier = Modifier,
                        color = Color.Black,
                        animationDuration = 800,
                        animationDelay = 200,
                        strokeWidth = 1.5.dp
                    )
                }
            }
        }
    }




    /*
        // on below line we are adding data to our list.
        courseList = courseList + ListModal("Dia dos Pais", "Dia dos Pais Promoções",  R.drawable.img)
        courseList = courseList + ListModal("Dia das Mães", "Dia das Mães Promoções", R.drawable.logo_temp)
        courseList = courseList + ListModal("Natal", "Festa de natal", R.drawable.logo_temp)
        courseList = courseList + ListModal("Promoções Semana", "Promoções da Semana", R.drawable.img)
        courseList = courseList + ListModal("Final de Semana", "Promoções Final de Semana", R.drawable.logo_temp)
        courseList = courseList + ListModal("Extra", "Promoçõe Extrar", R.drawable.img)

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

            Row{
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
            Row (modifier = Modifier.fillMaxWidth().padding(0.dp)){
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

            LazyColumn {
                itemsIndexed(courseList) { index, item ->
                    // on below line we are creating a card for our list view item.
                    androidx.compose.material.Card(
                        // inside our grid view on below line
                        // we are adding on click for each item of our grid view.
                        onClick = {
                            // inside on click we are displaying the toast message.
                            Toast.makeText(
                                context,
                                courseList[index].languageName + " selected..",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        // on below line we are adding
                        // padding from our all sides.
                        modifier = Modifier.padding(8.dp),

                        // on below line we are adding
                        // elevation for the card.
                        elevation = 6.dp
                    )
                    {
                        // on below line we are creating
                        // a row for our list view item.
                        Row(
                            // for our row we are adding modifier
                            // to set padding from all sides.
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            // on below line inside row we are adding spacer
                            Spacer(modifier = Modifier.width(5.dp))

                            // on below line we are adding image to display the image.
                            Image(
                                // on below line we are specifying the drawable image for our image.
                                painter = painterResource(id = courseList[index].languageImg),

                                // on below line we are specifying
                                // content description for our image
                                contentDescription = "Javascript",

                                // on below line we are setting height
                                // and width for our image.
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp)
                                    .padding(5.dp)
                            )

                            // on below line we are adding spacer between image and a text
                            Spacer(modifier = Modifier.width(5.dp))

                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = courseList[index].languageName,
                                    modifier = Modifier.padding(4.dp),
                                    color = Color.Black, textAlign = TextAlign.Center
                                )

                                Text(
                                    text = courseList[index].description,
                                    modifier = Modifier.padding(4.dp),
                                    color = Color.Black, textAlign = TextAlign.Center
                                )
                                Text(
                                    text = "08/10/2025 10:35",
                                    modifier = Modifier.padding(4.dp),
                                    color = Color.LightGray, textAlign = TextAlign.Right
                                )
                            }

                        }
                    }
                }
            }
        }*/
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