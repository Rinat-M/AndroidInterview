package com.rino.pressureandpulse.repositories

import com.rino.pressureandpulse.entities.Measurement
import kotlinx.coroutines.flow.Flow

interface MeasurementsRepository {

    fun getMeasurements(): List<Measurement>

    fun addMeasurement(measurement: Measurement)

}