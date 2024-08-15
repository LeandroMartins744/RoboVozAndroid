package com.example.myapplication.ui.theme.audios

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.*
import com.example.myapplication.R
import com.example.myapplication.data.ListAudiosModal
import com.example.myapplication.data.ListModal
import com.example.myapplication.ui.theme.JetPackBottomNavigationTheme
import com.example.myapplication.ui.theme.frame.Utils
import com.example.myapplication.ui.theme.frame.convertMillisToDate
import com.example.myapplication.ui.theme.frame.toBrazilianDateFormat
import java.util.ArrayList
import kotlin.math.min

class Audio2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetPackBottomNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AudioScreen2(LocalContext.current)
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AudioScreen2(context: Context) {
    var courseList: List<ListAudiosModal> = ArrayList<ListAudiosModal>()

    courseList = courseList + ListAudiosModal("Audio 01", R.drawable.logo_temp, "Descrição Audio Lista 01",  R.raw.audio_test)
    courseList = courseList + ListAudiosModal("Audio 02", R.drawable.logo_temp, "Descrição Audio Lista 02", R.raw.audio_test)
    courseList = courseList + ListAudiosModal("Audio 03", R.drawable.logo_temp, "Descrição Audio Lista 03", R.raw.audio_test)
    courseList = courseList + ListAudiosModal("Audio 04", R.drawable.logo_temp, "Descrição Audio Lista 04", R.raw.audio_test)
    courseList = courseList + ListAudiosModal("Audio 05", R.drawable.logo_temp, "Descrição Audio Lista 05", R.raw.audio_test)
    courseList = courseList + ListAudiosModal("Audio 06", R.drawable.logo_temp, "Descrição Audio Lista 06", R.raw.audio_test)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
            .wrapContentSize(Alignment.TopStart)
            .padding(20.dp)
    ) {

        Row {
            Utils().getSubTitle("Selecionando Audio")
        }
        Row (modifier = Modifier.fillMaxWidth().padding(0.dp)){

            LazyColumn {
                itemsIndexed(courseList) { index, item ->
                    Card(
                        onClick = {
                            Toast.makeText(
                                context,
                                courseList[index].name + " selected..",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        modifier = Modifier.padding(8.dp),
                        elevation = 6.dp
                    )
                    {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth()
                        ) {
                            Spacer(modifier = Modifier.width(5.dp))
                            Image(
                                painter = painterResource(id = courseList[index].image),
                                contentDescription = "Javascript",
                                modifier = Modifier
                                    .height(60.dp)
                                    .width(60.dp)
                                    .padding(5.dp)
                            )

                            Spacer(modifier = Modifier.width(5.dp))

                            Column(modifier = Modifier.fillMaxWidth()) {
                                androidx.compose.material.Text(
                                    text = courseList[index].description,
                                    modifier = Modifier.padding(4.dp),
                                    color = Color.Black, textAlign = TextAlign.Center
                                )

                                androidx.compose.material.Text(
                                    text = courseList[index].description,
                                    modifier = Modifier.padding(4.dp),
                                    color = Color.Black, textAlign = TextAlign.Center
                                )
                                androidx.compose.material.Text(
                                    text = "08/10/2025 10:35",
                                    modifier = Modifier.padding(4.dp),
                                    color = Color.LightGray, textAlign = TextAlign.Right
                                )
                            }

                        }
                    }
                }
            }

        }
    }
}