package com.example.movies.presentation.model.moviedetails

import android.content.Context
import com.example.movies.presentation.base.BaseUiState
import com.example.movies.presentation.utils.attachImageBaseUrl


data class MovieDetailsUIModel(
    val genres: List<GenreUIModel>?,
    val id: Long,
    val imdbId: String,
    val title: String,
    val overview: String,
    private val posterPath: String,
    val releaseDate: String,
    private val rate: Double
) : BaseUiState() {

    fun getImageUrl(context: Context): String {
        return attachImageBaseUrl(context, posterPath)
    }

    fun getRate(): String {
        return rate.toString()
    }

}