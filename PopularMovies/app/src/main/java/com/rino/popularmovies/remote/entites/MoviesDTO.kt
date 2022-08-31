package com.rino.popularmovies.remote.entites

data class MoviesDTO(
    val page: Int,
    val results: List<MovieDTO>
)