package com.rino.mapapp.base

import com.google.android.gms.maps.model.LatLng

data class MapMarker(val latLng: LatLng, val title: String = "", val description: String = "")