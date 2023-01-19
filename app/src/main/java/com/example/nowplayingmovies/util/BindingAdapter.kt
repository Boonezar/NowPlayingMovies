package com.example.nowplayingmovies.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.nowplayingmovies.R
import com.example.nowplayingmovies.data.Movie
import com.example.nowplayingmovies.data.MovieApiStatus

private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w600_and_h900_bestv2"

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieListAdapter
    adapter.submitList(data)
}

@BindingAdapter("movieApiStatus")
fun bindStatus(statusTextView: TextView, status: MovieApiStatus?) {
    when (status) {
        MovieApiStatus.DONE -> statusTextView.visibility = View.GONE
        MovieApiStatus.LOADING -> {
            statusTextView.setText(R.string.loading)
            statusTextView.visibility = View.VISIBLE
        }
        MovieApiStatus.ERROR -> {
            statusTextView.setText(R.string.error)
            statusTextView.visibility = View.VISIBLE
        }
        else -> { /* no-op */ }
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imageUrl: String?) {
    imageUrl?.let {
        val imageUri = "$POSTER_BASE_URL$it".toUri().buildUpon().scheme("https").build()
        imageView.load(imageUri) {
            placeholder(R.drawable.ic_loading_animation)
            error(R.drawable.ic_broken_image)
        }
    }
}
