package com.example.movies.domain.model.moviesdetails


data class MovieDetailsDomainModel(
    val genres: List<GenreDomainModel>?,
    val homepage: String,
    val id: Long,
    val imdbId: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val status: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double
)