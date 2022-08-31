package com.rino.popularmovies.datasources

import com.rino.popularmovies.remote.MovieDbService
import com.rino.popularmovies.remote.entites.MoviesDTO

class RemoteDataSourceImpl(private val movieDbService: MovieDbService) : DataSource {

    override suspend fun getPopularMovies(page: Int): MoviesDTO {
            val response = movieDbService.getPopularMovies(page = page)
            return response.body()!!
    }

}