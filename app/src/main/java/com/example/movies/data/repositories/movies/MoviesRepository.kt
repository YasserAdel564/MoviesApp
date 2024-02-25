package com.example.movies.data.repositories.movies

import androidx.paging.PagingData
import com.example.movies.domain.model.movies.MovieDomainModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getPopularMoviesPagedV2(): Flow<PagingData<MovieDomainModel>>
}