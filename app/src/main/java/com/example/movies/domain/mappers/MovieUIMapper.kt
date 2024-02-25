package com.example.movies.domain.mappers

import com.example.movies.domain.model.movies.MovieDomainModel
import com.example.movies.presentation.model.movies.MovieUIModel

object MovieUIMapper {
    fun mapMovieFromDomainToUIModel(domainModel: MovieDomainModel): MovieUIModel {
        return with(domainModel)
        {
            MovieUIModel(
                id = id,
                image = posterPath,
                title = title,
                releaseDate = releaseDate,
                description = overview,
                rate = voteAverage,
            )
        }
    }
}

