package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.R
import com.example.myapplication.util.LocalData
import com.example.myapplication.util.ValidFileLocal
import com.example.myapplication.view.interfaces.Alert
import com.example.myapplication.view.interfaces.Bars
import com.example.myapplication.view.interfaces.ButtonNew
import com.example.myapplication.view.pages.account.HomeAccount
import com.example.myapplication.view.pages.login.LoginActivity
import com.example.myapplication.view.pages.home.HomePage
import com.example.myapplication.view.theme.JetPackBottomNavigationTheme
import com.example.myapplication.view.theme.NavigationItem
import com.example.myapplication.view.pages.audios.AudioActivity
import com.example.myapplication.view.pages.audios.AudioList
import com.example.myapplication.view.pages.home.HomeInsertActivity
import com.example.myapplication.view.pages.playlist.PlayListActivity
import com.example.myapplication.view.pages.playlist.PlayListDetailsActivity
import com.example.myapplication.view.pages.playlist.PlaylistHome
import com.example.myapplication.view.pages.voices.VoicesHome

import com.example.myapplication.viewModel.AudioViewModel
import com.example.myapplication.viewModel.PlaylistViewModel
import com.example.myapplication.viewModel.SchedulingViewModel
import com.example.myapplication.viewModel.VoicesViewModel
import com.google.gson.Gson


class MainActivity : ComponentActivity() {
    private val viewModelPlaylist: PlaylistViewModel by viewModels()
    private val viewModelAudio: AudioViewModel by viewModels()
    private val viewModelVoices: VoicesViewModel by viewModels()
    private val viewModelScheduling: SchedulingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(LocalData(this).valid())
            this.startActivity(Intent(this, LoginActivity::class.java))

        setContent {
            JetPackBottomNavigationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    mainScreen()
                }
            }
        }
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
    }

    override fun onStart() {
        super.onStart()
        if(LocalData(this).valid())
            this.startActivity(Intent(this, LoginActivity::class.java))
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Audios,
            NavigationItem.Playlist,
            NavigationItem.Voices,
            NavigationItem.Config
        )
        BottomNavigation(
            backgroundColor = Color(R.color.primary),
            contentColor = Color.White
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(id = item.icon),
                            contentDescription = item.title,
                            tint = Color.White
                        )
                    },
                    label = { Text(text = item.title, color = Color.White) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.route,
                    onClick = {
                        load(item)
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun mainScreen() {
        val navController = rememberNavController()
        Scaffold(
            topBar = { Bars().topBar() },
            bottomBar = { BottomNavigationBar(navController) },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    navigation(navController = navController)
                }
            },
            backgroundColor = colorResource(R.color.primary) // Set background color to avoid the white flashing when you switch between screens
        )
        viewModelScheduling.get()
    }


    @Composable
    fun navigation(navController: NavHostController) {
        NavHost(navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
                HomePage().listSchedule(viewModelScheduling.loading, viewModelScheduling.schedulingListResponse, context = this@MainActivity)

                ButtonNew().actionButton {
                    this@MainActivity.startActivity(Intent(this@MainActivity, HomeInsertActivity::class.java))
                }
            }
            composable(NavigationItem.Audios.route) {

                AudioList().audios(viewModelAudio.loading, viewModelAudio.itemListResponse, validFileLocal = ValidFileLocal(this@MainActivity, "")){ p1 ->
                    val it = Intent(this@MainActivity, AudioActivity::class.java)
                    it.putExtra("object", Gson().toJson(p1))
                    this@MainActivity.startActivity(it)
                }

                ButtonNew().actionButton {
                    if(LocalData(this@MainActivity).getVoice().id == "")
                        Toast.makeText(this@MainActivity, "Para cadastrar Audios, você precisa selecionar a Voz Default", Toast.LENGTH_SHORT).show()
                    else
                        this@MainActivity.startActivity(Intent(this@MainActivity, AudioActivity::class.java))
                }
            }
            composable(NavigationItem.Playlist.route) {
                PlaylistHome().list(viewModelPlaylist.loading, viewModelPlaylist.playListResponse){ p1 ->
                    val it = Intent(this@MainActivity, PlayListDetailsActivity::class.java)
                    it.putExtra("object", Gson().toJson(p1))
                    this@MainActivity.startActivity(it)
                }

                ButtonNew().actionButton {
                    this@MainActivity.startActivity(Intent(this@MainActivity, PlayListActivity::class.java))
                }
            }
            composable(NavigationItem.Voices.route) {
                VoicesHome().List(viewModelVoices.loading, viewModelVoices.voicesResponse, context = this@MainActivity){ p1 ->
//                    val it = Intent(this@MainActivity, PlayListActivity::class.java)
//                    it.putExtra("object", Gson().toJson(p1))
//                    this@MainActivity.startActivity(it)
                }
            }
            composable(NavigationItem.Config.route) {
                HomeAccount().account(LocalContext.current)
            }
        }
    }

    private fun load(item: NavigationItem){
        when(item.route){
            NavigationItem.Home.route -> viewModelScheduling.get()
            NavigationItem.Playlist.route -> viewModelPlaylist.get()
            NavigationItem.Audios.route -> {
                viewModelAudio.setFile(ValidFileLocal(this@MainActivity, ""))
                viewModelAudio.get()
            }
            //NavigationItem.Audios.route -> viewModelAudio.downloadFile(this@MainActivity.filesDir.absoluteFile.toString()) //.get()
            NavigationItem.Voices.route -> viewModelVoices.get()
            NavigationItem.Config.route -> Log.e("Lit", "==========================   AUDIO  =================")
        }
    }
}