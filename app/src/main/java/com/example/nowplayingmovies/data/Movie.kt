package com.example.nowplayingmovies.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    val adult: Boolean,
    val overview: String,
    val id: String,
    val title: String,
    val popularity: Double,
    val video: Boolean,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "poster_path") val posterSrcUrl: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double
)
