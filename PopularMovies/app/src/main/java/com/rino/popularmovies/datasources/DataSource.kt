package com.rino.popularmovies.datasources

import com.rino.popularmovies.remote.entites.MovieDTO

interface DataSource {

    fun getPopularMovies(): Result<List<MovieDTO>>

}