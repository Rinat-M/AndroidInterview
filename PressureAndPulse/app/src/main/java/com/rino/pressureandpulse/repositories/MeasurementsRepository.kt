package com.rino.pressureandpulse.repositories

import com.rino.pressureandpulse.entities.Measurement
import kotlinx.coroutines.flow.Flow

interface MeasurementsRepository {

    fun getMeasurementsFlow(): Flow<List<Measurement>>

    fun addMeasurement(measurement: Measurement)

}