package com.example.nowplayingmovies

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.nowplayingmovies.data.Movie
import com.example.nowplayingmovies.data.MovieApiStatus
import com.example.nowplayingmovies.data.MovieRepository
import com.example.nowplayingmovies.network.MovieDatabaseApi
import kotlinx.coroutines.launch

class MoviesListViewModel(application: Application): AndroidViewModel(application) {
    private val movieRepository: MovieRepository
    private val _status = MutableLiveData<MovieApiStatus>()
    private val _movies = MutableLiveData<List<Movie>>()
    val status: LiveData<MovieApiStatus> = _status
    val movies: LiveData<List<Movie>> = _movies

    init {
        Log.d("MOVIES", "start viewModelInit")
        movieRepository = MovieRepository(application)
        getNowPlayingMovies()
    }

    suspend fun favoriteButtonClicked(movie: Movie) {
        Log.d("MOVIES", "favoriteButtonClicked: ${movie.title}")
        if (movie.isFavorite) {
            movieRepository.insert(movie)
        } else {
            movieRepository.delete(movie)
        }
    }

    private fun getNowPlayingMovies() {
        Log.d("MOVIES", "start getNowPlayingMovies")
        _status.value = MovieApiStatus.LOADING
        viewModelScope.launch {
            try {
                val movieResults = MovieDatabaseApi.retrofitService.getNowPlaying().results
                val favoriteMovies = movieRepository.getFavoriteMovies()
                for (movie in movieResults) {
                    if (favoriteMovies.any { it.title == movie.title }) {
                        movie.isFavorite = true
                    }
                }
                _movies.value = movieResults
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
