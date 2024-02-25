package com.example.movies.data.repositories.movies

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.movies.data.datasources.MoviesDataSource
import com.example.movies.data.mappers.MovieDomainMapper
import com.example.movies.domain.model.movies.MovieDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

const val PAGE_SIZE = 20
const val INITIAL_LOAD_SIZE = 20
const val PREFETCH_DISTANCE = 10

class MoviesRepositoryImpl(
    private val dataSource: MoviesDataSource,
) : MoviesRepository {

    override suspend fun getPopularMoviesPagedV2(): Flow<PagingData<MovieDomainModel>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                initialLoadSize = INITIAL_LOAD_SIZE,
                enablePlaceholders = false,
                prefetchDistance = PREFETCH_DISTANCE,
            ),
            pagingSourceFactory = {
                dataSource
            }
        ).flow.map {
            it.map {
                MovieDomainMapper.mapMovieFromRemoteToDomainModel(it)
            }
        }
    }
}