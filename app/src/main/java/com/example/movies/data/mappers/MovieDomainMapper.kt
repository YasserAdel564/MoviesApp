package com.example.movies.data.mappers

import com.example.movies.data.model.movies.MovieRemoteModel
import com.example.movies.domain.model.movies.MovieDomainModel

object MovieDomainMapper {
    fun mapMovieFromRemoteToDomainModel(remoteModel: MovieRemoteModel): MovieDomainModel {
        return with(remoteModel)
        {
            MovieDomainModel(
                backdropPath = backdropPath,
                id = id,
                overview = overview,
                posterPath = posterPath,
                releaseDate = releaseDate,
                title = title,
                voteAverage = voteAverage,
            )
        }
    }
}

