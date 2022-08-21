package com.rino.mapapp.base

sealed class Screen(val route: String) {
    object Maps : Screen(route = "maps")
    object Markers : Screen(route = "markers")
}