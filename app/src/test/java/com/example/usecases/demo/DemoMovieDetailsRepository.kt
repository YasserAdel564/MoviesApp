package com.example.usecases.demo

import com.example.movies.data.repositories.moviedetails.MovieDetailsRepository
import com.example.movies.data.utils.resource.NetworkResource
import com.example.movies.domain.model.moviesdetails.MovieDetailsDomainModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class DemoMovieDetailsRepository(private val dispatcher: CoroutineDispatcher) :
    MovieDetailsRepository {


    override suspend fun getMovieDetails(id: Long): NetworkResource<MovieDetailsDomainModel> {
        return withContext(dispatcher) {
            delay(500)
            val movie = DemoDS.list.find { it.id == id }
            if (movie != null) NetworkResource.success(
                MovieDetailsDomainModel(
                    id = movie.id,
                    genres = emptyList(),
                    homepage = "",
                    imdbId = "",
                    originalTitle = movie.title,
                    overview = movie.overview,
                    popularity = 0.0,
                    posterPath = movie.posterPath,
                    releaseDate = movie.releaseDate,
                    title = movie.title,
                    status = "",
                    video = false,
                    voteAverage = 0.0
                )
            )
            else NetworkResource.error(DemoDS.fakeServerErrorMessage)
        }
    }
}