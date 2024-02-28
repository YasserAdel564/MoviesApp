package com.example.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.movies.domain.mappers.MovieUIMapper
import com.example.movies.domain.usecases.movies.MoviesUseCase
import com.example.movies.presentation.model.movies.MovieUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val popularMoviesUseCase: MoviesUseCase
) : ViewModel() {

    private val _moviesState: MutableStateFlow<PagingData<MovieUIModel>> =
        MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<MovieUIModel>> get() = _moviesState

    init {
        viewModelScope.launch {
            getMovies()
        }
    }

    private suspend fun getMovies() {
        viewModelScope.launch {
            popularMoviesUseCase.invoke(this)
                .collect {
                    _moviesState.value = it.map { movie ->
                        MovieUIMapper.mapMovieFromDomainToUIModel(movie)
                    }
                }
        }
    }
}
