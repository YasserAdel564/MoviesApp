package com.example.movies.presentation.model.movies

import android.content.Context
import com.example.movies.presentation.base.BaseUiState
import com.example.movies.presentation.utils.attachImageBaseUrl

data class MovieUIModel(
    val id: Long,
    val title: String,
    private val image: String,
    val description: String,
    val releaseDate: String,
    private val rate: Double
) : BaseUiState() {

    fun getImageUrl(context: Context): String {
        return attachImageBaseUrl(context, image)
    }

    fun getRate(): String {
        return rate.toString()
    }

}