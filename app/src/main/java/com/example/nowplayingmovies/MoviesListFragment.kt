package com.example.nowplayingmovies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.nowplayingmovies.databinding.FragmentMoviesListBinding
import com.example.nowplayingmovies.util.MovieListAdapter

class MoviesListFragment : Fragment() {
    private lateinit var binding: FragmentMoviesListBinding
    private val viewModel: MoviesListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentMoviesListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = MovieListAdapter()

        Log.d("MOVIES", "viewModel.movies: ${viewModel.movies.value?.size ?: "null"}")

        return binding.root
    }
}