package com.example.movies.domain.usecases.movies

import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movies.data.repositories.movies.MoviesRepository
import com.example.movies.di.IoDispatcher
import com.example.movies.domain.model.movies.MovieDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    suspend operator fun invoke(scope: CoroutineScope): Flow<PagingData<MovieDomainModel>> {
        return moviesRepository.getPopularMoviesPagedV2().flowOn(dispatcher).cachedIn(scope)
    }

}