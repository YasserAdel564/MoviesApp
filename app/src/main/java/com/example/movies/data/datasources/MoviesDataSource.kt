package com.example.movies.data.datasources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movies.data.model.movies.MovieRemoteModel
import com.example.movies.data.network.MoviesApiService
import com.example.movies.data.network.safeApiCall
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MoviesDataSource @Inject constructor(
    private val service: MoviesApiService
) :
    PagingSource<Int, MovieRemoteModel>() {

    override fun getRefreshKey(state: PagingState<Int, MovieRemoteModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieRemoteModel> {

        return try {
            val nextPageNumber = when (params) {
                is LoadParams.Refresh -> 1
                is LoadParams.Append -> params.key.inc()
                is LoadParams.Prepend -> null
            }
            val response = safeApiCall {
                service.getPopularMovies(
                    page = nextPageNumber ?: 1
                )
            }
            return LoadResult.Page(
                data = response.data?.results ?: listOf(),
                prevKey = null,
                nextKey = if (response.data?.results == null) null else nextPageNumber
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
