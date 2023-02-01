package com.example.nowplayingmovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.nowplayingmovies.databinding.FragmentMoviesListBinding
import com.example.nowplayingmovies.util.MovieListAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment() {
    private lateinit var binding: FragmentMoviesListBinding
    private lateinit var adapter: MovieListAdapter
    private val viewModel: MoviesListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesListBinding.inflate(inflater)
        adapter = MovieListAdapter()
        adapter.favoriteButtonOnClick = { movie ->
            lifecycleScope.launch(Dispatchers.Default) { viewModel.favoriteButtonClicked(movie) }
        }
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = adapter

        return binding.root
    }
}