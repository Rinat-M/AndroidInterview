package com.rino.mapapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.core.app.ActivityCompat.recreate
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestLocationPermissions(onLocationEnabled: (Boolean) -> Unit) {
    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
        )
    )
    if (locationPermissionsState.allPermissionsGranted) {
        onLocationEnabled(true)
    } else {
        SideEffect {
            locationPermissionsState.launchMultiplePermissionRequest()
        }
    }
}
