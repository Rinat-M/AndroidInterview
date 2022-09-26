package com.rino.pressureandpulse.ui.screens

import androidx.lifecycle.ViewModel
import com.rino.pressureandpulse.entities.Measurement
import com.rino.pressureandpulse.repositories.MeasurementsRepository
import com.rino.pressureandpulse.utils.toStringFormat
import kotlinx.coroutines.flow.map

class MainViewModel(
    private val measurementsRepository: MeasurementsRepository
) : ViewModel() {

    val measurementsGroupedFlow = measurementsRepository.getMeasurementsFlow().map { list ->
        list.sortedByDescending { it.dateOfMeasurement }
            .groupBy { it.dateOfMeasurement.toStringFormat("dd MMMM") }
    }

    fun addMeasurement(measurement: Measurement) {
        measurementsRepository.addMeasurement(measurement)
    }
}