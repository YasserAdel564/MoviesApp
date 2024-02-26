package com.example.movies.presentation.ui.movies

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.movies.R
import com.example.movies.databinding.FragmentMoviesBinding
import com.example.movies.presentation.base.BaseFragment
import com.example.movies.presentation.ui.movies.adapter.MovieClickListener
import com.example.movies.presentation.ui.movies.adapter.MoviesAdapter
import com.example.movies.presentation.viewmodel.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(), MovieClickListener {

    private val viewModel by viewModels<MoviesViewModel>()
    private val adapter = MoviesAdapter(this)
    override val layout: Int
        get() = R.layout.fragment_movies


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadPopularMovies()
        binding.moviesRecycleView.adapter = adapter
    }

    override fun observeUiState() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state().collectLatest {
                handleProgressVisibility(false)
                adapter.submitData(it.list)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadingState().collectLatest {
                handleProgressVisibility(true)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            adapter.loadStateFlow.collect { loadState ->
                if (loadState.source.refresh is LoadState.Error) {
                    val error = (loadState.source.refresh as LoadState.Error).error
                    Toast.makeText(requireContext(), error.localizedMessage, Toast.LENGTH_SHORT)
                        .show()
                }

            }
        }

    }

    override fun onMovieItemClicked(id: Long) {
        findNavController().navigate(
            MoviesFragmentDirections.actionMoviesFragmentToMovieDetailsFragment(
                id
            )
        )
    }

    private fun handleProgressVisibility(isVisible: Boolean) {
        binding.progressBar.isVisible = isVisible
    }
}