package com.rino.pressureandpulse.entities

import java.util.Date

data class Measurement(
    val dateOfMeasurement: Date,
    val topPressure: Int,
    val lowerPressure: Int,
    val pulse: Int
)