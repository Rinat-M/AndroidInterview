package com.rino.objectmovement

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.rino.objectmovement.ui.theme.ObjectMovementTheme
import com.rino.objectmovement.utils.coordinateSystem
import com.rino.objectmovement.utils.getSinusoidPoints

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    ObjectMovementTheme {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            var showCoordinateSystem by remember { mutableStateOf(false) }

            Button(
                modifier = Modifier.wrapContentSize(),
                onClick = { showCoordinateSystem = !showCoordinateSystem }
            ) {
                Text("Coordinate system")
            }

            if (showCoordinateSystem) {
                SinusoidInCoordinateSystem()
            }

            MovingTaxi()
        }
    }
}

@Composable
fun SinusoidInCoordinateSystem() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        coordinateSystem(Color.Gray)
        val points = getSinusoidPoints(size)
        Log.d("TAG_DRAW", "Draw points")
        drawPoints(
            points = points,
            strokeWidth = 4f,
            pointMode = PointMode.Points,
            color = Color.Blue
        )
    }
}

@Composable
fun MovingTaxi() {
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        val points = getSinusoidPoints(
            Size(
                constraints.maxWidth.toFloat(),
                constraints.maxHeight.toFloat()
            )
        )

        val xShift = constraints.maxWidth.toFloat() * 0.18f
        val yShift = constraints.maxHeight.toFloat() * 0.1f

        val stepByPointsForAnimation = 10

        var isAnimated by remember { mutableStateOf(false) }
        var currentPointIndex by remember { mutableStateOf(0) }

        val animatedSizeOffset: Offset by animateOffsetAsState(
            targetValue = if (!isAnimated) {
                currentPointIndex = 0
                points[currentPointIndex]
            } else {
                currentPointIndex =
                    if (currentPointIndex + stepByPointsForAnimation < points.size) {
                        currentPointIndex + stepByPointsForAnimation
                    } else points.size - 1

                points[currentPointIndex]
            },
            animationSpec = tween(
                durationMillis = 50,
                easing = FastOutSlowInEasing
            ),
        )

        Image(
            painter = painterResource(R.drawable.ic_taxi),
            contentDescription = null,
            alignment = Alignment.Center,
            modifier = Modifier
                .width(100.dp)
                .offset {
                    IntOffset(
                        if (animatedSizeOffset.x > constraints.maxWidth - xShift) animatedSizeOffset.x.toInt() - xShift.toInt() else animatedSizeOffset.x.toInt() - 50,
                        if (animatedSizeOffset.y > constraints.maxWidth - yShift) animatedSizeOffset.y.toInt() - yShift.toInt() else animatedSizeOffset.y.toInt() - 50
                    )
                }
                .clickable { isAnimated = !isAnimated }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}