package com.example.myapplication.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.myapplication.view.interfaces.HomeInterface
import com.example.myapplication.view.theme.JetPackBottomNavigationTheme
import com.example.myapplication.view.theme.NavigationItem
import com.example.myapplication.view.theme.frame.Account
import com.example.myapplication.view.theme.frame.Audios
import com.example.myapplication.view.theme.frame.Playlist
import com.example.myapplication.viewModel.PlaylistViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: PlaylistViewModel by viewModels()

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
                    MainScreen()
                }
            }
        }
    }


    @Composable
    fun TopBar() {
        TopAppBar(
            title = { Image(painter = painterResource(R.drawable.logo), contentDescription = "") },
            backgroundColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        )
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        val items = listOf(
            NavigationItem.Home,
            NavigationItem.Music,
            NavigationItem.Movies,
            NavigationItem.Profile
        )
        BottomNavigation(
            backgroundColor = MaterialTheme.colorScheme.primary,
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
    fun MainScreen() {
        val navController = rememberNavController()
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNavigationBar(navController) },
            content = { padding ->
                Box(modifier = Modifier.padding(padding)) {
                    Navigation(navController = navController)
                }
            },
            backgroundColor = colorResource(R.color.purple_500) // Set background color to avoid the white flashing when you switch between screens
        )
        viewModel.get()
    }


    @Composable
    fun Navigation(navController: NavHostController) {
        NavHost(navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
                HomeInterface().List(viewModel.loading, viewModel.playListResponse, context = this@MainActivity)
            }
            composable(NavigationItem.Music.route) {
                Audios(LocalContext.current)
            }
            composable(NavigationItem.Movies.route) {
                Playlist().List(viewModel.loading, viewModel.playListResponse, context = this@MainActivity)
            }
            composable(NavigationItem.Profile.route) {
                Account(LocalContext.current)
            }
        }
    }

    private fun loadHome(){
        viewModel.get()
    }
    private fun loadAudio(){
        //viewModel.get()
        Log.e("Lit", "==========================   AUDIO  =================")
    }
    private fun loadPlay(){
        viewModel.get()
    }
    private fun loadConfig(){
        Log.e("Lit", "==========================   AUDIO  =================")
    }

    private fun load(item: NavigationItem){
        when(item.route){
            NavigationItem.Home.route -> loadHome()
            NavigationItem.Movies.route -> loadAudio()
            NavigationItem.Music.route -> loadPlay()
            NavigationItem.Profile.route -> loadConfig()
        }
    }
}