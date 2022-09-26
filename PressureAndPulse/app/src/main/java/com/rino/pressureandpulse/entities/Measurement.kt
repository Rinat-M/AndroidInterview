package com.rino.pressureandpulse.entities

import java.util.Date

data class Measurement(
    val topPressure: Int = 0,
    val lowerPressure: Int = 0,
    val pulse: Int = 0,
    val dateOfMeasurement: Date = Date(),
    val id: String = ""
) {

    fun toMap(): Map<String, Any> {
        val result = HashMap<String, Any>().apply {
            put(::topPressure.name, topPressure)
            put(::lowerPressure.name, lowerPressure)
            put(::pulse.name, pulse)
            put(::dateOfMeasurement.name, dateOfMeasurement)
        }

        return result
    }

}