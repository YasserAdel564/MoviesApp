package com.example.movies.data.network


import com.example.movies.data.model.movies.MoviesResponse
import com.example.movies.data.model.moviesdetails.MovieDetailsResponse
import com.example.movies.data.network.EndPoints.API_KEY
import com.example.movies.data.network.EndPoints.MOVIE_DETAILS
import com.example.movies.data.network.EndPoints.POPULAR_MOVIES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApiService {

    @GET(POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): Response<MoviesResponse>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") id: Long,
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<MovieDetailsResponse>
}