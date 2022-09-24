package com.rino.pressureandpulse.entities

import java.util.Date

data class Measurement(
    val topPressure: Int,
    val lowerPressure: Int,
    val pulse: Int,
    val dateOfMeasurement: Date = Date()
)