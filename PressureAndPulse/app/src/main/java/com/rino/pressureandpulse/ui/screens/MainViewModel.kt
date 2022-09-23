package com.rino.pressureandpulse.ui.screens

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.rino.pressureandpulse.repositories.MeasurementsRepository
import com.rino.pressureandpulse.utils.toStringFormat

class MainViewModel(
    private val measurementsRepository: MeasurementsRepository
) : ViewModel() {

    private val _measurements = measurementsRepository.getMeasurements().toMutableStateList()
    val measurementsGrouped = _measurements.groupBy { it.dateOfMeasurement.toStringFormat("dd MMMM") }
}