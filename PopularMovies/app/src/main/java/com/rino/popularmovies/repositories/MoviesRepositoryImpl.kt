package com.rino.popularmovies.repositories

import com.rino.popularmovies.datasources.DataSource
import com.rino.popularmovies.remote.entites.MovieDTO

class MoviesRepositoryImpl(
    private val dataSource: DataSource
) : MoviesRepository {

    override fun getPopularMovies(): Result<List<MovieDTO>> = dataSource.getPopularMovies()

}