package com.example.usecases.demo

import com.example.movies.domain.model.movies.MovieDomainModel


object DemoDS {
    const val validIdForTest: Long = 1072790
    const val invalidIdForTest: Long = 0L
    const val fakeServerErrorMessage = "The resource you requested could not be found."

    val validMovieForTest = MovieDomainModel(
        id = validIdForTest,
        backdropPath = "",
        overview = "Indiana Jones and the Dial of Destiny",
        posterPath = "",
        releaseDate = "2023",
        title = "Indiana",
        voteAverage = 3.4,
    )


    val list: List<MovieDomainModel> = listOf(
        validMovieForTest,
        MovieDomainModel(
            id = 1072790,
            backdropPath = "",
            overview = "After a migrating duck family alights on their pond with thrilling ",
            posterPath = "",
            releaseDate = "2020",
            title = "Migration",
            voteAverage = 3.2
        ),
        MovieDomainModel(
            id = 969492,
            backdropPath = "",
            overview = "Carol Danvers, aka Captain Marvel, has reclaimed her identity from the tyrannical",
            posterPath = "",
            releaseDate = "2021",
            title = "The Marvels",
            voteAverage = 3.3
        ), MovieDomainModel(
            id = 940551,
            backdropPath = "",
            overview = "After a deadly earthquake turns Seoul into a lawless badland",
            posterPath = "",
            releaseDate = "2022",
            title = "Badland Hunters",
            voteAverage = 3.4
        )
    )
}