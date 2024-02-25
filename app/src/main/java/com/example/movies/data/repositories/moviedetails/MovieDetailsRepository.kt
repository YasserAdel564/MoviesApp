package com.example.movies.data.repositories.moviedetails

import com.example.movies.data.utils.resource.NetworkResource
import com.example.movies.domain.model.moviesdetails.MovieDetailsDomainModel

interface MovieDetailsRepository {
    suspend fun getMovieDetails(id: Long): NetworkResource<MovieDetailsDomainModel>
}