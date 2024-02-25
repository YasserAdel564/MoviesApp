package com.example.movies.domain.mappers

import com.example.movies.domain.model.moviesdetails.GenreDomainModel
import com.example.movies.domain.model.moviesdetails.MovieDetailsDomainModel
import com.example.movies.presentation.model.moviedetails.GenreUIModel
import com.example.movies.presentation.model.moviedetails.MovieDetailsUIModel

object MovieDetailsUIMapper {
    fun mapMovieFromDomainToUIModel(domainModel: MovieDetailsDomainModel): MovieDetailsUIModel {
        return with(domainModel)
        {
            MovieDetailsUIModel(
                genres = genres?.map { mapGenreFromDomainToUIModel(it) },
                id = id,
                imdbId = imdbId,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                rate = voteAverage
            )
        }
    }

    private fun mapGenreFromDomainToUIModel(domainModel: GenreDomainModel): GenreUIModel {
        return with(domainModel)
        {
            GenreUIModel(
                id = id,
                name = name
            )
        }
    }
}

