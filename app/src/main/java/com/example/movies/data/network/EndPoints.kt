package com.example.movies.data.network

object EndPoints {
    private const val V3 = "3"
    private const val MOVIE = "movie"
    const val POPULAR_MOVIES = "$V3/$MOVIE/popular"
    const val MOVIE_DETAILS = "$V3/$MOVIE/{movie_id}"
    const val API_KEY = "c9856d0cb57c3f14bf75bdc6c063b8f3"
}