package com.example.movies.di

import com.example.movies.data.datasources.MovieDetailsRemoteDataSource
import com.example.movies.data.datasources.MoviesDataSource
import com.example.movies.data.repositories.moviedetails.MovieDetailsRepository
import com.example.movies.data.repositories.moviedetails.MovieDetailsRepositoryImpl
import com.example.movies.data.repositories.movies.MoviesRepository
import com.example.movies.data.repositories.movies.MoviesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object ReposModule {

    @Provides
    fun provideMoviesRepo(
        dataSource: MoviesDataSource
    ): MoviesRepository {
        return MoviesRepositoryImpl(
            dataSource
        )
    }

    @Provides
    fun provideMovieDetailsRepo(
        dataSource: MovieDetailsRemoteDataSource,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): MovieDetailsRepository {
        return MovieDetailsRepositoryImpl(
            dataSource,
            dispatcher
        )
    }

}