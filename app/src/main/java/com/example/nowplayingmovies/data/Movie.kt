package com.example.nowplayingmovies.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
@Entity(tableName = "favorite_movies_table")
data class Movie(
    val adult: Boolean,
    val overview: String,
    @PrimaryKey val id: String,
    val title: String,
    val popularity: Double,
    val video: Boolean,
    @Json(name = "original_title") val originalTitle: String,
    @Json(name = "original_language") val originalLanguage: String,
    @Json(name = "backdrop_path") val backdropPath: String?,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "poster_path") val posterSrcUrl: String,
    @Json(name = "release_date") val releaseDate: String,
    @Json(name = "vote_average") val voteAverage: Double,
    var isFavorite: Boolean = false
)
