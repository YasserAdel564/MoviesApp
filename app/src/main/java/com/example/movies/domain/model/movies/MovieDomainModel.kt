package com.example.movies.domain.model.movies

data class MovieDomainModel(
    val id: Long,
    val backdropPath: String?,
    val overview: String,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
)