package com.example.movies.data.model

import com.google.gson.annotations.SerializedName

abstract class BaseResponse {
    @SerializedName("status_message")
    var statusMessage: String? = ""
}