package com.rino.pressureandpulse.ui.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.rino.pressureandpulse.R
import com.rino.pressureandpulse.entities.Measurement
import com.rino.pressureandpulse.utils.toStringFormat
import java.util.*

@Composable
fun MeasurementDialog(
    measurement: Measurement,
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: (Measurement) -> Unit
) {
    val calendar = Calendar.getInstance()
    var dateOfMeasurement by remember { mutableStateOf(measurement.dateOfMeasurement) }

    var showCalendarDialog by remember { mutableStateOf(false) }
    var dateString by remember { mutableStateOf(calendar.time.toStringFormat()) }

    var showTimeDialog by remember { mutableStateOf(false) }
    var timeString by remember { mutableStateOf(calendar.time.toStringFormat("HH:mm")) }

    var topPressure by remember { mutableStateOf(0) }
    var lowerPressure by remember { mutableStateOf(0) }
    var pulse by remember { mutableStateOf(0) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.height(480.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                DateTimeSection(
                    dateValue = dateString, timeValue = timeString,
                    onDateClick = { showCalendarDialog = !showCalendarDialog },
                    onTimeClick = { showTimeDialog = !showTimeDialog },
                )
                PressureSelectionSection(
                    onTopPressureChosen = { value -> topPressure = value.toInt() },
                    onLowerPressureChosen = { value -> lowerPressure = value.toInt() })
                PulseSelectionSection(onPulseChosen = { value -> pulse = value.toInt() })
                ButtonsSection(onNegativeClick = onNegativeClick, onPositiveClick = {
                    val measurement = Measurement(
                        topPressure = topPressure,
                        lowerPressure = lowerPressure,
                        pulse = pulse,
                        dateOfMeasurement = dateOfMeasurement
                    )
                    onPositiveClick(measurement)
                })
            }
        }
    }

    if (showCalendarDialog) {
        CalendarDialog(
            onDismiss = { showCalendarDialog = !showCalendarDialog },
            onDateChange = { year, month, dayOfMonth ->
                calendar.time = dateOfMeasurement
                calendar.set(year, month, dayOfMonth)
                dateOfMeasurement = calendar.time
                dateString = calendar.time.toStringFormat()
                showCalendarDialog = !showCalendarDialog
            }
        )
    }

    if (showTimeDialog) {
        val initialValue = Pair(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE))
        TimeDialog(
            initialValue = initialValue,
            onDismiss = { showTimeDialog = !showTimeDialog },
            onTimeChange = { hour, minute ->
                calendar.time = dateOfMeasurement
                calendar.set(Calendar.HOUR_OF_DAY, hour)
                calendar.set(Calendar.MINUTE, minute)
                dateOfMeasurement = calendar.time
                timeString = calendar.time.toStringFormat("HH:mm")
                showTimeDialog = !showTimeDialog
            }
        )
    }
}

@Composable
fun DateTimeSection(
    dateValue: String,
    timeValue: String,
    onDateClick: () -> Unit,
    onTimeClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = dateValue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onDateClick() }
        )
        Spacer(modifier = Modifier.size(24.dp))
        Text(
            text = timeValue,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable { onTimeClick() }
        )
    }
}

@Composable
fun PressureSelectionSection(
    onTopPressureChosen: (String) -> Unit,
    onLowerPressureChosen: (String) -> Unit,
) {
    val topPressureValues = (0..238).map { it.toString() }
    val lowerPressureValues = (0..118).map { it.toString() }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        InfiniteItemsPicker(
            items = topPressureValues,
            firstIndex = topPressureValues.size / 2,
            onItemSelected = onTopPressureChosen
        )
        Text(
            text = "/",
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        InfiniteItemsPicker(
            items = lowerPressureValues,
            firstIndex = lowerPressureValues.size / 2,
            onItemSelected = onLowerPressureChosen
        )
    }
}

@Composable
fun InfiniteItemsPicker(
    items: List<String>,
    firstIndex: Int,
    onItemSelected: (String) -> Unit,
) {

    val listState = rememberLazyListState(firstIndex)
    val currentValue = remember { mutableStateOf("") }

    LaunchedEffect(key1 = !listState.isScrollInProgress) {
        onItemSelected(currentValue.value)
        listState.animateScrollToItem(index = listState.firstVisibleItemIndex)
    }

    Box(modifier = Modifier.height(122.dp)) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            state = listState,
            content = {
                items(count = Int.MAX_VALUE) {
                    val index = it % items.size
                    if (it == listState.firstVisibleItemIndex + 1) {
                        currentValue.value = items[index]
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = items[index],
                        modifier = Modifier.alpha(if (it == listState.firstVisibleItemIndex + 1) 1f else 0.3f),
                        style = MaterialTheme.typography.body1,
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        )
    }
}

@Composable
fun PulseSelectionSection(
    onPulseChosen: (String) -> Unit,
) {
    val pulseValues = (0..140).map { it.toString() }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_favorite),
            tint = Color.Red,
            contentDescription = "Pulse",
            modifier = Modifier
                .padding(top = 8.dp)
                .size(32.dp)
        )
        Spacer(modifier = Modifier.size(16.dp))
        InfiniteItemsPicker(
            items = pulseValues,
            firstIndex = pulseValues.size / 2,
            onItemSelected = onPulseChosen
        )
    }
}

@Composable
fun ButtonsSection(
    onNegativeClick: () -> Unit,
    onPositiveClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Button(onClick = onNegativeClick) {
            Text(text = "Cancel")
        }
        Button(onClick = onPositiveClick) {
            Text(text = "Оk")
        }
    }
}