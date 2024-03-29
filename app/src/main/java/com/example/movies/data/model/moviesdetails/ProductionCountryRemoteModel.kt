package com.example.movies.data.model.moviesdetails

import com.google.gson.annotations.SerializedName

data class ProductionCountryRemoteModel(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
)
