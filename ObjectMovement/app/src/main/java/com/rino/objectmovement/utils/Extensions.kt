package com.rino.objectmovement.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope

fun DrawScope.coordinateSystem(color: Color) {
    val middleW = size.width / 2
    val middleH = size.height / 2
    drawLine(
        color = color,
        start = Offset(0f, middleH),
        end = Offset(size.width - 1, middleH),
        strokeWidth = 2f
    )
    drawPath(
        path = Path().apply {
            moveTo(middleW, 0f)
            relativeLineTo(-20f, 20f)
            relativeLineTo(40f, -0F)
            close()
        },
        Color.Gray,
    )
    drawLine(
        color = color,
        start = Offset(middleW, 0f),
        end = Offset(middleW, size.height - 1),
        strokeWidth = 3f
    )
    drawPath(
        path = Path().apply {
            moveTo(size.width - 1, middleH)
            relativeLineTo(-20f, 20f)
            relativeLineTo(0f, -40F)
            close()
        },
        Color.Gray,
    )
}