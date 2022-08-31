package com.rino.popularmovies.remote

import com.rino.popularmovies.remote.entites.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbService {

    @GET("3/movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int
    ): Response<MoviesDTO>

}