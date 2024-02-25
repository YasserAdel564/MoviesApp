package com.example.movies.data.repositories.moviedetails

import com.example.movies.data.datasources.MovieDetailsRemoteDataSource
import com.example.movies.data.mappers.MovieDetailsDomainMapper
import com.example.movies.data.utils.resource.NetworkResource
import com.example.movies.domain.model.moviesdetails.MovieDetailsDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDetailsRepositoryImpl(
    private val dataSource: MovieDetailsRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : MovieDetailsRepository {

    override suspend fun getMovieDetails(id: Long): NetworkResource<MovieDetailsDomainModel> {
        return withContext(dispatcher) {
            dataSource.getMovieDetails(id)
                .map {
                    MovieDetailsDomainMapper.mapMovieFromRemoteToDomainModel(it)
                }
        }
    }
}