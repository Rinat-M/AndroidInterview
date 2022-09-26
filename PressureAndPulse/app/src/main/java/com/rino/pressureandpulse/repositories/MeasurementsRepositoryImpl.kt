package com.rino.pressureandpulse.repositories

import com.rino.pressureandpulse.datasources.MeasurementsSource
import com.rino.pressureandpulse.entities.Measurement
import kotlinx.coroutines.flow.Flow

class MeasurementsRepositoryImpl(
    private val measurementsSource: MeasurementsSource
) : MeasurementsRepository {

    override fun getMeasurementsFlow(): Flow<List<Measurement>> {
        return measurementsSource.getMeasurementsFlow()
    }

    override fun addMeasurement(measurement: Measurement) {
        measurementsSource.addMeasurement(measurement)
    }

    override fun updateMeasurement(measurement: Measurement) {
        measurementsSource.updateMeasurement(measurement)
    }

}