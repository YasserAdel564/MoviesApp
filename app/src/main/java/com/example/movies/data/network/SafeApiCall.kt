package com.example.movies.data.network

import com.example.movies.data.model.BaseResponse
import com.example.movies.data.utils.resource.NetworkResource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException


suspend fun <Response : BaseResponse> safeApiCall(apiCall: suspend () -> retrofit2.Response<Response>): NetworkResource<Response> {
    return withContext(Dispatchers.IO) {
        try {
            val response = apiCall.invoke()
            if (response.isSuccessful) {
                NetworkResource.success(response.body())
            } else {
                NetworkResource.error(response.message())
            }
        } catch (e: HttpException) {
            e.printStackTrace()
            try {
                val errorBody = e.response()?.errorBody()?.string() ?: ""
                val baseResponse = Gson().fromJson(errorBody, BaseResponse::class.java)
                NetworkResource.error(errorMessage = baseResponse.statusMessage)
            } catch (ee: Exception) {
                NetworkResource.error(errorMessage = e.localizedMessage)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResource.error(errorMessage = e.localizedMessage)
        }
    }
}