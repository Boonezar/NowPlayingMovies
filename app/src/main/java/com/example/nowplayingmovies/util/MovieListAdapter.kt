package com.example.nowplayingmovies.util

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.nowplayingmovies.R
import com.example.nowplayingmovies.data.Movie
import com.example.nowplayingmovies.databinding.MovielistItemBinding

class MovieListAdapter: ListAdapter<Movie, MovieListAdapter.MovieViewHolder>(DiffCallback) {
    var favoriteButtonOnClick: ((Movie) -> Unit)? = null

    inner class MovieViewHolder(private var binding: MovielistItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movie = movie
            binding.executePendingBindings()
            val star = binding.favoriteIcon
            setStarImageResource(star, movie)
            star.setOnClickListener {
                movie.isFavorite = !movie.isFavorite
                favoriteButtonOnClick?.invoke(movie)
                setStarImageResource(star, movie)
            }
        }

        private fun setStarImageResource(star: ImageView, movie: Movie) {
            star.setImageResource(if(movie.isFavorite) R.drawable.ic_star_full else R.drawable.ic_star_border)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(MovielistItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        object DiffCallback : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}
