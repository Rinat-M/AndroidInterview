package com.rino.pressureandpulse.datasources

import com.rino.pressureandpulse.entities.Measurement
import kotlinx.coroutines.flow.Flow

interface MeasurementsSource {

    fun getMeasurements(): List<Measurement>

    fun getMeasurementsFlow(): Flow<List<Measurement>>

    fun deleteMeasurement(id: String)

    fun addMeasurement(measurement: Measurement)

    fun updateMeasurement(measurement: Measurement)

}