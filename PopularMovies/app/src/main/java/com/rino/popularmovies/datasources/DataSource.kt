package com.rino.popularmovies.datasources

import com.rino.popularmovies.remote.entites.MoviesDTO

interface DataSource {

    suspend fun getPopularMovies(page: Int): MoviesDTO

}