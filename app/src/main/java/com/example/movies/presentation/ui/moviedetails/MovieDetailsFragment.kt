package com.example.movies.presentation.ui.moviedetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.movies.R
import com.example.movies.databinding.FragmentMovieDetailsBinding
import com.example.movies.presentation.base.BaseFragment
import com.example.movies.presentation.model.moviedetails.MovieDetailsUIModel
import com.example.movies.presentation.utils.loadImageUrl
import com.example.movies.presentation.viewmodel.MovieDetailsViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {

    override val layout: Int
        get() = R.layout.fragment_movie_details

    private val viewModel by viewModels<MovieDetailsViewModel>()
    private val args by navArgs<MovieDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadMovieDetails(args.id)
        initNavigationClickListener()
    }

    private fun initNavigationClickListener() {
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun observeUiState() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loadingState().collectLatest {
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state().collectLatest { movieDetails ->
                handleSuccessState(movieDetails)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.errorUiState().collectLatest { error ->
                context?.let {
                    Toast.makeText(
                        requireContext(),
                        error.getMessage(it),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun handleSuccessState(movieDetails: MovieDetailsUIModel) {
        with(binding)
        {
            context?.let { loadImageUrl(movieImage, movieDetails.getImageUrl(it)) }
            movieTitleTextView.text = movieDetails.title
            movieDescriptionTextView.text = movieDetails.overview
            movieDateTextView.text = movieDetails.releaseDate
            movieRateTextView.text = movieDetails.getRate()
            movieDetails.genres?.forEach { genre ->
                binding.movieGenreChipGroup.addView(Chip(requireContext()).apply {
                    text = genre.name
                })
            }
        }
    }
}