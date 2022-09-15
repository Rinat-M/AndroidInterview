package com.rino.redditinfinitylistapp.di

import com.rino.redditinfinitylistapp.repositories.RedditRepository
import com.rino.redditinfinitylistapp.repositories.RedditRepositoryImpl
import com.rino.redditinfinitylistapp.ui.screens.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Network
    single { NetworkModule.getOkHttpClient() }
    single { NetworkModule.getRetrofit(get()) }
    single { NetworkModule.getRedditService(get()) }

    // Repositories
    single<RedditRepository> { RedditRepositoryImpl(redditService = get()) }

    //View Models
    viewModel { MainViewModel(redditRepository = get()) }
}