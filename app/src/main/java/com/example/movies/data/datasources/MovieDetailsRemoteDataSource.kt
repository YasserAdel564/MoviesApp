package com.example.movies.data.datasources

import com.example.movies.data.model.moviesdetails.MovieDetailsResponse
import com.example.movies.data.network.MoviesApiService
import com.example.movies.data.network.safeApiCall
import com.example.movies.data.utils.resource.NetworkResource
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieDetailsRemoteDataSource @Inject constructor(
    private val api: MoviesApiService
) {
    suspend fun getMovieDetails(id: Long): NetworkResource<MovieDetailsResponse> {
        return safeApiCall {
            api.getMovieDetails(id)
        }
    }
}