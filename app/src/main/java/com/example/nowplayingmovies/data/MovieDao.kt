package com.example.nowplayingmovies.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert
    suspend fun insert(movie: Movie)
    @Delete
    suspend fun delete(movie: Movie)
    @Query("SELECT * from favorite_movies_table")
    suspend fun getFavoriteMovies(): List<Movie>
}