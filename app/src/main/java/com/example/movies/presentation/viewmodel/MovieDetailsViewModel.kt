package com.example.movies.presentation.viewmodel

import com.example.movies.presentation.model.moviedetails.MovieDetailsUIModel
import com.example.movies.data.utils.resource.Status
import com.example.movies.domain.mappers.MovieDetailsUIMapper
import com.example.movies.domain.usecases.moviedetails.MovieDetailsUseCase
import com.example.movies.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterIsInstance
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase
) : BaseViewModel<MovieDetailsUIModel>() {

    override fun state(): Flow<MovieDetailsUIModel> {
        return state.filterIsInstance()
    }


    fun loadMovieDetails(id: Long) {
        launchInViewModelScope {
            movieDetailsUseCase.invoke(id).collectLatest { result ->
                when (result.status) {
                    Status.LOADING -> triggerLoadingState()
                    Status.SUCCESS -> result.data?.let {
                        triggerUiState(
                            MovieDetailsUIMapper.mapMovieFromDomainToUIModel(
                                it
                            )
                        )
                    }

                    Status.ERROR -> triggerErrorState(result.errorMessage)
                }
            }
        }
    }

}