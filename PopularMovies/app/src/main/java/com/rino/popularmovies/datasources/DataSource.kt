package com.rino.popularmovies.datasources

import com.rino.popularmovies.remote.entites.MovieDetailsDTO
import com.rino.popularmovies.remote.entites.MoviesDTO

interface DataSource {

    suspend fun getPopularMovies(page: Int): MoviesDTO

    suspend fun getMovieDetails(movieId: Long): MovieDetailsDTO

}