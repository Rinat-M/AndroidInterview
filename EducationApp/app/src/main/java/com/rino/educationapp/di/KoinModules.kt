package com.rino.educationapp.di

import com.rino.educationapp.core.repositories.DummyRepositoryImpl
import com.rino.educationapp.core.repositories.MainRepository
import com.rino.educationapp.ui.screens.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<MainRepository> { DummyRepositoryImpl() }

    // ViewModels
    viewModel { MainViewModel(mainRepository = get()) }
}