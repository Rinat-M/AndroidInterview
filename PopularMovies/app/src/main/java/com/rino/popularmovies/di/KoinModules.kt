package com.rino.popularmovies.di

import com.rino.popularmovies.datasources.DataSource
import com.rino.popularmovies.datasources.RemoteDataSourceImpl
import com.rino.popularmovies.repositories.MoviesRepository
import com.rino.popularmovies.repositories.MoviesRepositoryImpl
import org.koin.dsl.module

val appModule = module {
    single<DataSource> { RemoteDataSourceImpl(get()) }
    single<MoviesRepository> { MoviesRepositoryImpl(get()) }

    // Network
    single { NetworkModule.getOkHttpClient() }
    single { NetworkModule.getRetrofit(get()) }
    single { NetworkModule.getMovieDbService(get()) }
}