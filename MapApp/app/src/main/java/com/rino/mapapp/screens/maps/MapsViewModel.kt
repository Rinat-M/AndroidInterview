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

    private var selectedMarker: MapMarker? = null

    fun add(item: MapMarker) {
        _markers.add(item)
    }

    fun remove(item: MapMarker) {
        _markers.remove(item)
    }

    fun setSelectedMarker(marker: MapMarker) {
        selectedMarker = marker
    }

    fun getSelectedMarker() = selectedMarker

    fun updateMarkerInList(title: String, description: String) {
        val indexOfMarker = _markers.indexOf(selectedMarker)
        if (indexOfMarker != -1) {
            val marker = selectedMarker!!
            val modifiedMarker = marker.copy(title = title, description = description)
            _markers[indexOfMarker] = modifiedMarker
            selectedMarker = modifiedMarker
        }
    }

}