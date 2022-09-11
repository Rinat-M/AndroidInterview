package com.rino.educationapp.ui.base

sealed class Screen(val name: String, val route: String) {
    object Home : Screen(name = "Home", route = "home")
    object Classes : Screen(name = "Classes", route = "classes")
    object Homework : Screen(name = "Homework", route = "homework")
    object Favorites : Screen(name = "Favorites", route = "favorites")
}
