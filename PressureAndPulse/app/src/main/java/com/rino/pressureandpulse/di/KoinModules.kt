package com.rino.pressureandpulse.di

import com.rino.pressureandpulse.datasources.FirebaseSourceImpl
import com.rino.pressureandpulse.datasources.MeasurementsSource
import com.rino.pressureandpulse.repositories.MeasurementsRepository
import com.rino.pressureandpulse.repositories.MeasurementsRepositoryImpl
import com.rino.pressureandpulse.ui.screens.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Data Sources
    single<MeasurementsSource> { FirebaseSourceImpl() }

    // Repositories
    single<MeasurementsRepository> { MeasurementsRepositoryImpl(measurementsSource = get()) }

    // View Models
    viewModel { MainViewModel(measurementsRepository = get()) }
}