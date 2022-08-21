package com.rino.mapapp.screens.maps

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import com.rino.mapapp.base.MapMarker

class MapsViewModel : ViewModel() {
    val defaultMarker = MapMarker(
        LatLng(55.751244, 37.618423),
        "Moscow",
        "Marker in Moscow"
    )

    private val _markers = listOf(defaultMarker).toMutableStateList()
    val markers: List<MapMarker> get() = _markers

    fun add(item: MapMarker) {
        _markers.add(item)
    }

    fun remove(item: MapMarker) {
        _markers.remove(item)
    }
}