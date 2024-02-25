package com.example.movies.presentation.viewmodel

import androidx.paging.map
import com.example.movies.domain.mappers.MovieUIMapper
import com.example.movies.domain.usecases.movies.MoviesUseCase
import com.example.movies.presentation.base.BaseViewModel
import com.example.movies.presentation.ui.movies.MoviesUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val popularMoviesUseCase: MoviesUseCase
) : BaseViewModel<MoviesUiState>() {

    override fun state(): Flow<MoviesUiState> {
        return state.filterIsInstance()
    }


    fun loadPopularMovies() {
        launchInViewModelScope { scope ->
            popularMoviesUseCase.invoke(scope).collectLatest {
                triggerUiState(MoviesUiState(
                    list = it.map { movie ->
                        MovieUIMapper.mapMovieFromDomainToUIModel(movie)
                    }
                ))
            }
        }
    }
}