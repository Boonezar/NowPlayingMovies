package com.example.nowplayingmovies.network

import com.example.nowplayingmovies.data.Movie
import com.example.nowplayingmovies.data.NowPlayingResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.themoviedb.org/3/"
private const val API_KEY = "7bfe007798875393b05c5aa1ba26323e"
private val moshi = Moshi
    .Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit
    .Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface MovieDatabaseApiService {
    @GET("movie/now_playing?api_key=$API_KEY")
    suspend fun getNowPlaying(): NowPlayingResult
}

object MovieDatabaseApi {
    val retrofitService: MovieDatabaseApiService by lazy {
        retrofit.create(MovieDatabaseApiService::class.java)
    }
}
