package com.rino.objectmovement.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlin.math.sin

fun getSinusoidPoints(size: Size): List<Offset> {
    val middleH = size.height / 2
    val points = mutableListOf<Offset>()

    for (x in 0 until size.width.toInt()) {
        val y = (sin(x * (2f * Math.PI / size.width)) * middleH + middleH).toFloat()
        points.add(Offset(x.toFloat(), y))
    }

    return points
}