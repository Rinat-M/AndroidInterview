package com.rino.pressureandpulse.repositories

import com.rino.pressureandpulse.entities.Measurement
import java.util.*

class DummyMeasurementsRepositoryImpl : MeasurementsRepository {
    private val fakeMeasurements = listOf(
        Measurement(150, 83, 55, Date(1635015960000)),
        Measurement(129, 79, 55, Date(1634964660000)),
        Measurement(141, 64, 63, Date(1635102420000)),
        Measurement(127, 73, 58, Date(1635046740000)),
        Measurement(137, 71, 59, Date(1635195240000)),
        Measurement(126, 67, 49, Date(1635138060000)),
        Measurement(150, 83, 55, Date(1635274860000)),
        Measurement(129, 79, 55, Date(1635222720000)),
        Measurement(137, 71, 59, Date(1635360240000)),
        Measurement(126, 67, 49, Date(1635314640000)),
    )


    override fun getMeasurements(): List<Measurement> {
        return fakeMeasurements.sortedByDescending { it.dateOfMeasurement }
    }

}