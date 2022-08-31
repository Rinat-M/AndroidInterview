package com.rino.popularmovies.ui.screens.moviedetails

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rino.popularmovies.remote.entites.MovieDetailsDTO
import com.rino.popularmovies.repositories.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel(
    private val movieId: String,
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private var _movieDetails: MovieDetailsDTO? by mutableStateOf(null)
    val movieDetails: MovieDetailsDTO? get() = _movieDetails

    private var _isLoaded: Boolean by mutableStateOf(false)
    val isLoaded: Boolean get() = _isLoaded

    init {
        Log.d("MovieDetailsViewModel", "movieId = $movieId")

        viewModelScope.launch(Dispatchers.IO) {
            _movieDetails = moviesRepository.getMovieDetails(movieId = movieId.toLong())
        }.invokeOnCompletion { _isLoaded = true }
    }

}