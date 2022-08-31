package com.rino.popularmovies.ui.base

sealed class Screen(val route: String) {
    object Main : Screen(route = "main")
    object MovieDetails : Screen(route = "movie_details")
}
