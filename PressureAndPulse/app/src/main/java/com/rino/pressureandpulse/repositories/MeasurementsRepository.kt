package com.rino.pressureandpulse.repositories

import com.rino.pressureandpulse.entities.Measurement

interface MeasurementsRepository {

    fun getMeasurements(): List<Measurement>

}