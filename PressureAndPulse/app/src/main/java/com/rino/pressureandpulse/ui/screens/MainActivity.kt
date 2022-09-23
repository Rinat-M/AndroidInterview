package com.rino.pressureandpulse.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rino.pressureandpulse.R
import com.rino.pressureandpulse.entities.Measurement
import com.rino.pressureandpulse.ui.theme.*
import com.rino.pressureandpulse.utils.toStringFormat
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp(mainViewModel)
        }
    }
}

@Composable
fun MyApp(mainViewModel: MainViewModel) {
    PressureAndPulseTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(title = { Text(text = "Pressure and Pulse") })
            },
            floatingActionButtonPosition = FabPosition.End,
            floatingActionButton = {
                FloatingActionButton(
                    backgroundColor = Color.Red,
                    modifier = Modifier.padding(16.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_favorite),
                        contentDescription = "Added",
                        tint = Color.White,
                        modifier = Modifier.size(28.dp)
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
            ) {
                MeasurementsList(mainViewModel.measurementsGrouped)
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MeasurementsList(grouped: Map<String, List<Measurement>>) {
    LazyColumn {
        grouped.forEach { (header, measurements) ->
            stickyHeader {
                MeasurementsListHeader(header)
                Divider(color = Color.LightGray, thickness = 1.dp)
            }

            items(measurements) { measurement ->
                val color = if (isAbnormalPressure(measurement)) YellowA100 else LightGreenA100
                MeasurementsListItem(measurement, color, modifier = Modifier.animateItemPlacement())
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun MeasurementsListHeader(header: String, modifier: Modifier = Modifier) {
    Text(
        text = header,
        fontSize = 18.sp,
        color = Grey600,
        fontWeight = FontWeight.Bold,
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Grey50, Color.White)
                )
            )
            .padding(8.dp)
    )
}

@Composable
fun MeasurementsListItem(item: Measurement, color: Color, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                Brush.radialGradient(
                    colors = listOf(color, Color.White),
                    radius = 400f
                )
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = item.dateOfMeasurement.toStringFormat("HH:mm"),
            color = Grey400,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        )
        Pressure(item)
        Pulse(item.pulse)
    }
}

@Composable
fun Pressure(item: Measurement) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = item.topPressure.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 26.sp
        )
        Text(
            text = "/",
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Text(
            text = item.lowerPressure.toString(),
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 26.sp
        )
    }
}

@Composable
fun Pulse(pulse: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(end = 16.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_favorite),
            tint = Color.Gray,
            contentDescription = "Pulse",
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = pulse.toString(),
            color = Color.Gray,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}

fun isAbnormalPressure(measurement: Measurement): Boolean =
    measurement.lowerPressure < 60 || measurement.lowerPressure > 80
            || measurement.topPressure < 110 || measurement.topPressure > 139