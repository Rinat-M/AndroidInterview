package com.rino.mapapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.rino.mapapp.ui.theme.MapAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MapAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyGoogleMap()
                }
            }
        }
    }
}

@Composable
private fun MyGoogleMap() {
    val moscow = LatLng(55.751244, 37.618423)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(moscow, 10f)
    }

    val mapUiSettings by remember { mutableStateOf(MapUiSettings(myLocationButtonEnabled = true)) }
    val mapProperties by remember { mutableStateOf(MapProperties(isMyLocationEnabled = false)) }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        properties = mapProperties,
        onMyLocationButtonClick = { false }
    ) {
        Marker(
            state = MarkerState(position = moscow),
            title = "Moscow",
            snippet = "Marker in Moscow"
        )
    }
}