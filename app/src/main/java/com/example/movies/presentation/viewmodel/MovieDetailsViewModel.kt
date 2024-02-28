package com.example.movies.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.domain.mappers.MovieDetailsUIMapper
import com.example.movies.domain.usecases.moviedetails.MovieDetailsUseCase
import com.example.movies.presentation.model.moviedetails.MovieDetailsUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: MovieDetailsUseCase,
) : ViewModel() {
    private val _movieDetailsState: MutableStateFlow<MovieDetailsUIModel?> =
        MutableStateFlow(value = null)
    val movieDetailsState: MutableStateFlow<MovieDetailsUIModel?> get() = _movieDetailsState


    suspend fun getMovieDetails(id: Long) {
        viewModelScope.launch {
            movieDetailsUseCase.invoke(id)
                .collect {
                    it.data?.let { movieDetailsDomainModel ->
                        _movieDetailsState.value =
                            MovieDetailsUIMapper.mapMovieFromDomainToUIModel(movieDetailsDomainModel)
                    }
                }
        }
    }

}