package com.rino.educationapp.core

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.*
import androidx.compose.ui.unit.Constraints
import com.rino.educationapp.ui.theme.Green400
import com.rino.educationapp.ui.theme.Grey800

fun Modifier.rotateVertically(rotation: VerticalRotation) = then(
    object : LayoutModifier {
        override fun MeasureScope.measure(
            measurable: Measurable,
            constraints: Constraints
        ): MeasureResult {
            val placeable = measurable.measure(constraints)
            return layout(placeable.height, placeable.width) {
                placeable.place(
                    x = -(placeable.width / 2 - placeable.height / 2),
                    y = -(placeable.height / 2 - placeable.width / 2)
                )
            }
        }

        override fun IntrinsicMeasureScope.minIntrinsicHeight(
            measurable: IntrinsicMeasurable,
            width: Int
        ): Int {
            return measurable.maxIntrinsicWidth(width)
        }

        override fun IntrinsicMeasureScope.maxIntrinsicHeight(
            measurable: IntrinsicMeasurable,
            width: Int
        ): Int {
            return measurable.maxIntrinsicWidth(width)
        }

        override fun IntrinsicMeasureScope.minIntrinsicWidth(
            measurable: IntrinsicMeasurable,
            height: Int
        ): Int {
            return measurable.minIntrinsicHeight(height)
        }

        override fun IntrinsicMeasureScope.maxIntrinsicWidth(
            measurable: IntrinsicMeasurable,
            height: Int
        ): Int {
            return measurable.maxIntrinsicHeight(height)
        }
    })
    .then(rotate(rotation.value))

enum class VerticalRotation(val value: Float) {
    CLOCKWISE(90f), COUNTER_CLOCKWISE(270f)
}

fun Modifier.background(isAdditional: Boolean): Modifier {
    return if (isAdditional) {
        this.background(Brush.linearGradient(colors = listOf(Green400, Color.Cyan)))
    } else {
        this.background(Grey800)
    }
}

fun Modifier.bottomNavItemBackground(isSelected: Boolean): Modifier {
    return if (isSelected) {
        this.background(Brush.linearGradient(colors = listOf(Color.Gray, Color.Transparent)))
    } else {
        this.background(Color.DarkGray)
    }
}