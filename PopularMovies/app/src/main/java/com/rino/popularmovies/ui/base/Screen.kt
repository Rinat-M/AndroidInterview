package com.rino.popularmovies.ui.base

sealed class Screen(val route: String) {
    object Main : Screen(route = "main")
    object MovieDetails : Screen(route = "movie_details")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
