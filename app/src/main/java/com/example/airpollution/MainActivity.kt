package com.example.airpollution

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.airpollution.screen.MainScreen
import com.example.airpollution.screen.SetLocationScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val appState = MainAppState(navController)
            MaterialTheme {
                NavigationComponent(
                    navController,
                    appState
                )
            }
        }
    }

    @Composable
    fun NavigationComponent(
        navController: NavHostController,
        appState: MainAppState
    ) {
        val context = LocalContext.current
        val dataStore = StoreDistrictName(context)
        var startDestination by remember { mutableStateOf("") }

        val settingValue = dataStore.settingValue.collectAsState(initial = false).value
        startDestination = if (settingValue) MAIN_SCREEN else SET_LOCATION_SCREEN

        NavHost(
            navController = navController,
            startDestination = startDestination
        ){
            composable(SET_LOCATION_SCREEN) {
                SetLocationScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
            }
            composable(MAIN_SCREEN) {
                MainScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
            }
        }
    }
}
