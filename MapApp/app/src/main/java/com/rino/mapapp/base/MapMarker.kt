package com.rino.mapapp.base

import com.google.android.gms.maps.model.LatLng

data class MapMarker(
    val latLng: LatLng,
    val title: String = "not specified",
    val description: String = "not specified"
)