package com.rino.pressureandpulse.repositories

import com.rino.pressureandpulse.entities.Measurement
import java.util.*

class DummyMeasurementsRepositoryImpl : MeasurementsRepository {
    private val fakeMeasurements = listOf(
        Measurement(Date(1635015960000), 150, 83, 55),
        Measurement(Date(1634964660000), 129, 79, 55),
        Measurement(Date(1635102420000), 141, 64, 63),
        Measurement(Date(1635046740000), 127, 73, 58),
        Measurement(Date(1635195240000), 137, 71, 59),
        Measurement(Date(1635138060000), 126, 67, 49),
        Measurement(Date(1635274860000), 150, 83, 55),
        Measurement(Date(1635222720000), 129, 79, 55),
        Measurement(Date(1635360240000), 137, 71, 59),
        Measurement(Date(1635314640000), 126, 67, 49),
    )


    override fun getMeasurements(): List<Measurement> {
        return fakeMeasurements.sortedByDescending { it.dateOfMeasurement }
    }

}