package com.rino.mapapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@Composable
fun MapsScreen(
    modifier: Modifier = Modifier,
    mapsViewModel: MapsViewModel = viewModel()
) {

    var isMapLoaded by remember {
        mutableStateOf(false)
    }
    var mapUiSettings by remember {
        mutableStateOf(MapUiSettings(myLocationButtonEnabled = false))
    }
    var mapProperties by remember {
        mutableStateOf(MapProperties(isMyLocationEnabled = false))
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(mapsViewModel.defaultMarker.latLng, 10f)
    }

    RequestLocationPermissions { isMyLocationEnabled ->
        mapProperties = mapProperties
            .copy(isMyLocationEnabled = isMyLocationEnabled)
        mapUiSettings = mapUiSettings
            .copy(myLocationButtonEnabled = isMyLocationEnabled)
    }

    MyGoogleMap(
        modifier = modifier,
        mapProperties = mapProperties,
        mapUiSettings = mapUiSettings,
        cameraPositionState = cameraPositionState,
        markers = mapsViewModel.markers,
        onMapLoaded = { isMapLoaded = true },
        onMapClick = { point ->
            mapsViewModel.add(
                MapMarker(point, "{Lat=${point.latitude};Lng=${point.longitude}")
            )
        }
    )

    ProgressIndicator(loadingIsComplete = isMapLoaded)
}

@Composable
private fun MyGoogleMap(
    modifier: Modifier = Modifier,
    mapProperties: MapProperties,
    mapUiSettings: MapUiSettings,
    cameraPositionState: CameraPositionState,
    markers: List<MapMarker>,
    onMapLoaded: () -> Unit = {},
    onMapClick: (LatLng) -> Unit = {}
) {
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        uiSettings = mapUiSettings,
        properties = mapProperties,
        onMapLoaded = onMapLoaded,
        onMapClick = onMapClick
    ) {
        markers.forEach { mapMarker ->
            Marker(
                state = MarkerState(position = mapMarker.latLng),
                title = mapMarker.title,
                snippet = mapMarker.description
            )
        }
    }
}

@Composable
private fun ProgressIndicator(loadingIsComplete: Boolean, modifier: Modifier = Modifier) {
    if (!loadingIsComplete) {
        AnimatedVisibility(
            modifier = modifier.fillMaxSize(),
            visible = !loadingIsComplete,
            enter = EnterTransition.None,
            exit = fadeOut()
        ) {
            CircularProgressIndicator(
                modifier = modifier
                    .background(MaterialTheme.colors.background)
                    .wrapContentSize()
            )
        }
    }
}