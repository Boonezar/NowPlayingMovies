package com.example.nowplayingmovies.data

import android.content.Context

class MovieRepository(context: Context) {
    private val movieDao: MovieDao

    init {
        movieDao = MovieRoomDb.getInstance(context).movieDao()
    }

    suspend fun getFavoriteMovies() = movieDao.getFavoriteMovies()

    suspend fun insert(movie: Movie) = movieDao.insert(movie)

    suspend fun delete(movie: Movie) = movieDao.delete(movie)
}
