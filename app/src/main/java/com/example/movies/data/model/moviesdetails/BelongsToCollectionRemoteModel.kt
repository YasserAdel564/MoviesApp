package com.example.movies.data.model.moviesdetails

import com.google.gson.annotations.SerializedName

data class BelongsToCollectionRemoteModel(
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("poster_path")
    val posterPath: String
)