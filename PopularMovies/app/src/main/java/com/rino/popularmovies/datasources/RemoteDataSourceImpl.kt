package com.rino.popularmovies.datasources

import com.rino.popularmovies.remote.MovieDbService
import com.rino.popularmovies.remote.entites.MovieDTO

class RemoteDataSourceImpl(private val movieDbService: MovieDbService) : DataSource {

    override fun getPopularMovies(): Result<List<MovieDTO>> {
        return try {
            val response = movieDbService.getPopularMovies().execute()

            if (!response.isSuccessful) {
                return Result.failure(Exception("Response code: ${response.code()}. " +
                        "Response message: ${response.errorBody()?.string()}"))
            }

            val moviesDto = response.body()
            Result.success(moviesDto?.results ?: listOf())
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

}