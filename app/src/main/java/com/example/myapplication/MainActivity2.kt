package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.interfaces.LoginField
import com.example.myapplication.ui.theme.MyLoginApplicationTheme

//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            MyLoginApplicationTheme {
//                MainContent()
//            }
//        }
//    }
//}
class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            
            MyLoginApplicationTheme {
                MainContent()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainContent() {
    Scaffold(
        topBar = {
            setToobar()
        },//,  backgroundColor = Color(0xff0f9d58)) },
        content = { Greeting("sdjkhfgdjksfhdjks") },
        bottomBar = {

        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun setToobar(){
    TopAppBar(
        title = { Image(painter = painterResource(R.drawable.logo), contentDescription = "") },
        //title = {  Text("GFG | Drop Down Menu", color = Color.Red) },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

//@Composable
//fun setBoobar(){
//    BottomAppBar (
//        title = { Image(painter = painterResource(R.drawable.logo), contentDescription = "") },
//        //title = {  Text("GFG | Drop Down Menu", color = Color.Red) },
//        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
//    )
//}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Hello $name!",
                modifier = modifier
            )
        }
    }
}

@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun GreetingPreview() {
    MyLoginApplicationTheme {
        Greeting("Android")
    }
}

@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun GreetingPreviewDark() {
    MyLoginApplicationTheme(darkTheme = true) {
        Greeting("Android")
    }
}