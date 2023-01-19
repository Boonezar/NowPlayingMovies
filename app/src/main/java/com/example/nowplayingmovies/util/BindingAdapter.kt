package com.example.nowplayingmovies.util

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nowplayingmovies.R
import com.example.nowplayingmovies.data.Movie
import com.example.nowplayingmovies.data.MovieApiStatus

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