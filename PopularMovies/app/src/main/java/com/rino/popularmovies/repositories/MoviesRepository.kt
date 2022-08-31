package com.rino.popularmovies.repositories

import com.rino.popularmovies.remote.entites.MovieDetailsDTO
import com.rino.popularmovies.remote.entites.MoviesDTO

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): MoviesDTO

    suspend fun getMovieDetails(movieId: Long): MovieDetailsDTO

}