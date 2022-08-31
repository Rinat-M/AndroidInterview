package com.rino.popularmovies.di

import com.rino.popularmovies.datasources.DataSource
import com.rino.popularmovies.datasources.RemoteDataSourceImpl
import com.rino.popularmovies.repositories.MoviesRepository
import com.rino.popularmovies.repositories.MoviesRepositoryImpl
import com.rino.popularmovies.ui.screens.main.MainViewModel
import com.rino.popularmovies.ui.screens.moviedetails.MovieDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<DataSource> { RemoteDataSourceImpl(get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }

    // Network
    single { NetworkModule.getOkHttpClient() }
    single { NetworkModule.getRetrofit(get()) }
    single { NetworkModule.getMovieDbService(get()) }

    // View Models
    viewModel { MainViewModel(moviesRepository = get()) }
    viewModel { parameters ->
        MovieDetailsViewModel(
            movieId = parameters.get(),
            moviesRepository = get()
        )
    }
}