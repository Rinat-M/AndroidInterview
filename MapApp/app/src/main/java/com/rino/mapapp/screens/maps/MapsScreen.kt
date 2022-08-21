package com.rino.mapapp.screens.maps

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import com.rino.mapapp.RequestLocationPermissions
import com.rino.mapapp.base.MapMarker

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

    MarkersIconButton(modifier = modifier, onButtonClick = {})
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

@Composable
private fun MarkersIconButton(
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {

            Button(onClick = onButtonClick) {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = null
                )
                Text(text = "Markers")
            }
        }
    }
}