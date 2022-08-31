package com.rino.popularmovies.datasources

import com.rino.popularmovies.remote.MovieDbService
import com.rino.popularmovies.remote.entites.MovieDetailsDTO
import com.rino.popularmovies.remote.entites.MoviesDTO

class RemoteDataSourceImpl(private val movieDbService: MovieDbService) : DataSource {

    override suspend fun getPopularMovies(page: Int): MoviesDTO {
        val response = movieDbService.getPopularMovies(page = page)
        return response.body()!!
    }

    override suspend fun getMovieDetails(movieId: Long): MovieDetailsDTO {
        val response = movieDbService.getMovieDetails(movieId = movieId)
        return response.body()!!
    }

}