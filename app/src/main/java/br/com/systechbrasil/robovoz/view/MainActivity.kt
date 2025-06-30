package br.com.systechbrasil.robovoz.view

import myColor
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Scaffold
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import br.com.systechbrasil.robovoz.R
import br.com.systechbrasil.robovoz.util.LocalData
import br.com.systechbrasil.robovoz.view.interfaces.Bars
import br.com.systechbrasil.robovoz.view.interfaces.ButtonNew
import br.com.systechbrasil.robovoz.view.pages.account.HomeAccount
import br.com.systechbrasil.robovoz.view.pages.home.*
import br.com.systechbrasil.robovoz.view.theme.JetPackBottomNavigationTheme
import br.com.systechbrasil.robovoz.view.theme.NavigationItem
import br.com.systechbrasil.robovoz.view.pages.login.LoginActivity
import br.com.systechbrasil.robovoz.view.pages.playlist.PlayLisFragment
import br.com.systechbrasil.robovoz.view.pages.playlist.PlayListActivity
import br.com.systechbrasil.robovoz.view.pages.playlist.PlayListDetailsActivity
import br.com.systechbrasil.robovoz.view.pages.playlist.PlaylistHome
import br.com.systechbrasil.robovoz.view.pages.voices.VoicesHome

import br.com.systechbrasil.robovoz.viewModel.AudioViewModel
import br.com.systechbrasil.robovoz.viewModel.PlaylistViewModel
import br.com.systechbrasil.robovoz.viewModel.SchedulingViewModel
import br.com.systechbrasil.robovoz.viewModel.VoicesViewModel
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

    override fun onBackPressed() {
        super.onBackPressed()
    }


    override fun onStart() {
        super.onStart()
        if(LocalData(this).valid())
            this.startActivity(Intent(this, LoginActivity::class.java))
        viewModelPlaylist.get()

    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        val items = listOf(
            NavigationItem.Home,
            //NavigationItem.Audios,
            NavigationItem.Playlist,
            NavigationItem.Voices,
            NavigationItem.Config
        )
        BottomNavigation(
            backgroundColor = myColor.default,
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


    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun navigation(navController: NavHostController) {
        NavHost(navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
                BackHandler(true) {}
                val playActions = HomeSetPlayerList(this@MainActivity, viewModelPlaylist)
                HomePage().listSchedule(viewModelScheduling.loading, viewModelScheduling.schedulingListResponse,
                    onClickList = { p1, p2 -> playActions.setPlayerList(p1, p2) },
                    onDelete = { p1 ->
                    viewModelScheduling.delete(p1)
                    Toast.makeText(this@MainActivity, "Item removido com sucesso!", Toast.LENGTH_SHORT).show()
                    viewModelScheduling.get()
                })

                homePlayerPlaylist(viewModelPlaylist, playActions) {
                    viewModelPlaylist.openDialogPlayer = false
                }

                ButtonNew().actionButton {
                    this@MainActivity.startActivity(Intent(this@MainActivity, HomeInsertActivity::class.java))
                }
            }
//
            composable(NavigationItem.Playlist.route) {
                BackHandler(true) {  }
                PlaylistHome().list(PlayLisFragment(this@MainActivity, viewModelPlaylist, viewModelAudio))
            }
            composable(NavigationItem.Voices.route) {
                BackHandler(true) {}
                VoicesHome().List(viewModelVoices.loading, viewModelVoices.voicesResponse, context = this@MainActivity){ p1 ->
//                    val it = Intent(this@MainActivity, PlayListActivity::class.java)
//                    it.putExtra("object", Gson().toJson(p1))
//                    this@MainActivity.startActivity(it)
                }
            }
            composable(NavigationItem.Config.route) {
                BackHandler(true) {}
                HomeAccount().account(LocalContext.current)
            }
        }
    }

    private fun load(item: NavigationItem){
        when(item.route){
            NavigationItem.Home.route -> viewModelScheduling.get()
            NavigationItem.Playlist.route -> viewModelPlaylist.get()
//            NavigationItem.Audios.route -> {
//                viewModelAudio.setFile(ValidFileLocal(this@MainActivity, ""))
//                viewModelAudio.get()
//            }
            //NavigationItem.Audios.route -> viewModelAudio.downloadFile(this@MainActivity.filesDir.absoluteFile.toString()) //.get()
            NavigationItem.Voices.route -> viewModelVoices.get()
            NavigationItem.Config.route -> Log.e("Lit", "==========================   AUDIO  =================")
        }
    }
}