package com.example.nowplayingmovies.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class MovieRoomDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        @Volatile private var instance: MovieRoomDb? = null

        fun getInstance(context: Context): MovieRoomDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): MovieRoomDb {
            println("SAM in buildDatabase")
            return Room.databaseBuilder(context.applicationContext, MovieRoomDb::class.java, "movies-db")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}