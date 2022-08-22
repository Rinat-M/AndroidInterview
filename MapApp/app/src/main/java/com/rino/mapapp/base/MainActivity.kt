package com.rino.mapapp.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rino.mapapp.screens.maps.MapsScreen
import com.rino.mapapp.screens.maps.MapsViewModel
import com.rino.mapapp.screens.markeritem.MarkerItemScreen
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
    val mapsViewModel: MapsViewModel = viewModel()

    MapAppTheme {
        NavHost(
            navController = navController,
            startDestination = Screen.Maps.route
        ) {
            composable(Screen.Maps.route) {
                MapsScreen(
                    mapsViewModel = mapsViewModel,
                    onNavigateToMarkers = { navController.navigate(Screen.Markers.route) })
            }
            composable(Screen.Markers.route) {
                MarkersScreen(
                    mapsViewModel = mapsViewModel,
                    onNavigateToMarkerItem = { navController.navigate(Screen.MarkerItem.route) }
                )
            }
            composable(Screen.MarkerItem.route) {
                MarkerItemScreen(mapsViewModel = mapsViewModel)
            }
        }
    }
}
