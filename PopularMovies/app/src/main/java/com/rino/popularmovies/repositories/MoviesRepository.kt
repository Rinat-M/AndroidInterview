package com.rino.popularmovies.repositories

import com.rino.popularmovies.remote.entites.MovieDTO

interface MoviesRepository {

    fun getPopularMovies(): Result<List<MovieDTO>>

}