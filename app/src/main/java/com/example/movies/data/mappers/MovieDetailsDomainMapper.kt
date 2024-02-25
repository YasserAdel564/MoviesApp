package com.example.movies.data.mappers

import com.example.movies.data.model.moviesdetails.GenreRemoteModel
import com.example.movies.data.model.moviesdetails.MovieDetailsResponse
import com.example.movies.domain.model.moviesdetails.GenreDomainModel
import com.example.movies.domain.model.moviesdetails.MovieDetailsDomainModel

object MovieDetailsDomainMapper {
    fun mapMovieFromRemoteToDomainModel(remoteModel: MovieDetailsResponse): MovieDetailsDomainModel {
        return with(remoteModel)
        {
            MovieDetailsDomainModel(
                genres = genres?.map { mapGenreFromRemoteToDomainModel(it) },
                homepage = homepage,
                id = id,
                imdbId = imdbId,
                originalTitle = originalTitle,
                overview = overview,
                popularity = popularity,
                posterPath = posterPath,
                releaseDate = releaseDate,
                status = status,
                title = title,
                video = video,
                voteAverage = voteAverage,
            )
        }
    }

    private fun mapGenreFromRemoteToDomainModel(remoteModel: GenreRemoteModel): GenreDomainModel {
        return with(remoteModel)
        {
            GenreDomainModel(
                id = id,
                name = name
            )
        }
    }
}

