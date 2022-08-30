package com.rino.popularmovies.remote

import com.rino.popularmovies.remote.entites.MoviesDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieDbService {

    @GET("3/movie/popular")
    fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): Call<MoviesDTO>

}