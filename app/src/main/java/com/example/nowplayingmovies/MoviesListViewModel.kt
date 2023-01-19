package com.example.nowplayingmovies

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nowplayingmovies.data.Movie
import com.example.nowplayingmovies.data.MovieApiStatus
import com.example.nowplayingmovies.network.MovieDatabaseApi
import kotlinx.coroutines.launch

class MoviesListViewModel: ViewModel() {
    private val _status = MutableLiveData<MovieApiStatus>()
    private val _movies = MutableLiveData<List<Movie>>()
    val status: LiveData<MovieApiStatus> = _status
    val movies: LiveData<List<Movie>> = _movies

    init {
        Log.d("MOVIES", "start viewModelInit")
        getNowPlayingMovies()
    }

    private fun getNowPlayingMovies() {
        Log.d("MOVIES", "start getNowPlayingMovies")
        _status.value = MovieApiStatus.LOADING
        viewModelScope.launch {
            try {
                _movies.value = MovieDatabaseApi.retrofitService.getNowPlaying().results
                _status.value = MovieApiStatus.DONE
                Log.d("MOVIES", "finish getNowPlayingMovies, size: ${_movies.value?.size ?: "size is null"}")
            } catch (e: Exception) {
                _movies.value = emptyList()
                _status.value = MovieApiStatus.ERROR
                Log.e("MOVIES", "Error in getNowPlayingMovies: $e")
            }
        }
    }
}
