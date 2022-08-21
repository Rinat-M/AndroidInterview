package com.rino.mapapp.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rino.mapapp.screens.maps.MapsScreen
import com.rino.mapapp.screens.markers.MarkersScreen
import com.rino.mapapp.ui.theme.MapAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    MapAppTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.Maps.route
        ) {
            composable(Screen.Maps.route) {
                MapsScreen(onNavigateToMarkers = { navController.navigate(Screen.Markers.route) })
            }
            composable(Screen.Markers.route) { MarkersScreen() }
        }
    }
}
