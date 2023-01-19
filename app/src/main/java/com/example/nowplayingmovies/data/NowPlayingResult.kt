package com.example.nowplayingmovies.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NowPlayingResult(
    val results: List<Movie>
)
