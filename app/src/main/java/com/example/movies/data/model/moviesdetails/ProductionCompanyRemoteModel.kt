package com.example.movies.data.model.moviesdetails

import com.google.gson.annotations.SerializedName

data class ProductionCompanyRemoteModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("logo_path")
    val logoPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("origin_country")
    val originCountry: String
)