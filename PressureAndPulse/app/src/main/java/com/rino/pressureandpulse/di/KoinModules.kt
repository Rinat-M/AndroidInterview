package com.rino.pressureandpulse.di

import com.rino.pressureandpulse.repositories.DummyMeasurementsRepositoryImpl
import com.rino.pressureandpulse.repositories.MeasurementsRepository
import com.rino.pressureandpulse.ui.screens.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Repositories
    single<MeasurementsRepository> { DummyMeasurementsRepositoryImpl() }

    // View Models
    viewModel { MainViewModel(measurementsRepository = get()) }
}