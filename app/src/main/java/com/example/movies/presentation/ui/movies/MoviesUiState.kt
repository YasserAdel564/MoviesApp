package com.example.movies.presentation.ui.movies

import androidx.paging.PagingData
import com.example.movies.presentation.base.BaseUiState
import com.example.movies.presentation.model.movies.MovieUIModel

data class MoviesUiState(
    val list: PagingData<MovieUIModel>
) : BaseUiState()