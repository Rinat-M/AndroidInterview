package com.rino.popularmovies.repositories

import com.rino.popularmovies.datasources.DataSource
import com.rino.popularmovies.remote.entites.MovieDetailsDTO
import com.rino.popularmovies.remote.entites.MoviesDTO

class MoviesRepositoryImpl(
    private val dataSource: DataSource
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): MoviesDTO {
        return dataSource.getPopularMovies(page)
    }

    override suspend fun getMovieDetails(movieId: Long): MovieDetailsDTO {
        return dataSource.getMovieDetails(movieId)
    }

}