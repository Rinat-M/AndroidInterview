package com.rino.popularmovies.remote.entites

import com.google.gson.annotations.SerializedName
import java.util.*

data class MovieDTO(
    val id: Long,
    @SerializedName("poster_path")
    val posterPath: String,
    val adult: Boolean,
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: Date,
    @SerializedName("genre_ids")
    val genreIds: List<Int>,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("original_language")
    val originalLanguage: String,
    val title: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    val popularity: Double,
    @SerializedName("vote_count")
    val voteCount: Int,
    val video: Boolean,
    @SerializedName("vote_average")
    val voteAverage: Double,
    var isFavorite: Boolean? = false,
    val budget: Long? = null,
    val imdbId: String? = null,
    val revenue: Long? = null,
    val tagline: String? = null,
    val director: String? = null
)