package com.rino.popularmovies.repositories

import com.rino.popularmovies.remote.entites.MoviesDTO

interface MoviesRepository {

    suspend fun getPopularMovies(page: Int): MoviesDTO

}