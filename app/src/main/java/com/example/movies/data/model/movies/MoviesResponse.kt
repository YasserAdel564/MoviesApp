package com.example.movies.data.model.movies

import com.example.movies.data.model.BaseResponse

class MoviesResponse(
    val results: List<MovieRemoteModel>?
) : BaseResponse()