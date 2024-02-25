package com.example.movies.domain.usecases.moviedetails

import com.example.movies.data.repositories.moviedetails.MovieDetailsRepository
import com.example.movies.data.utils.resource.NetworkResource
import com.example.movies.di.IoDispatcher
import com.example.movies.domain.model.moviesdetails.MovieDetailsDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MovieDetailsUseCase @Inject constructor(
    private val repo: MovieDetailsRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) {

    operator fun invoke(id: Long): Flow<NetworkResource<MovieDetailsDomainModel>> {
        return flow {
            emit(repo.getMovieDetails(id))
        }.flowOn(dispatcher)
    }
}