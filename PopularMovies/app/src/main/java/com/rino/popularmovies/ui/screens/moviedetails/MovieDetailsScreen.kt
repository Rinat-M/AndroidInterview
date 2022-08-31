package com.rino.popularmovies.ui.screens.moviedetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

const val MOVIE_ID_ARG = "movieId"

@Composable
fun MovieDetailsScreen(movieId: String) {
    val detailsViewModel = getViewModel<MovieDetailsViewModel> { parametersOf(movieId) }

    Box() {
        Text("Movie Details $movieId - ${detailsViewModel.movieDetails?.id}")
    }

    ProgressIndicator(detailsViewModel.isLoaded)
}

@Composable
private fun ProgressIndicator(loadingIsComplete: Boolean, modifier: Modifier = Modifier) {
    if (!loadingIsComplete) {
        AnimatedVisibility(
            modifier = modifier.fillMaxSize(),
            visible = !loadingIsComplete,
            enter = EnterTransition.None,
            exit = fadeOut()
        ) {
            CircularProgressIndicator(
                modifier = modifier
                    .background(MaterialTheme.colors.background)
                    .wrapContentSize()
            )
        }
    }
}