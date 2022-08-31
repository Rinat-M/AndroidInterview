package com.rino.popularmovies.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rino.popularmovies.ui.base.Screen
import com.rino.popularmovies.ui.screens.moviedetails.MovieDetailsScreen
import com.rino.popularmovies.ui.theme.PopularMoviesTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(mainViewModel)
        }
    }
}

@Composable
fun MyApp(mainViewModel: MainViewModel) {
    val navController = rememberNavController()

    PopularMoviesTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
        }
        NavHost(
            navController = navController,
            startDestination = Screen.Main.route
        ) {
            composable(Screen.Main.route) {
                TopMoviesScreen(
                    viewModel = mainViewModel,
                    onNavigateToMovieDetails = { navController.navigate(Screen.MovieDetails.route) }
                )
            }
            composable(Screen.MovieDetails.route) { MovieDetailsScreen() }
        }
    }
}