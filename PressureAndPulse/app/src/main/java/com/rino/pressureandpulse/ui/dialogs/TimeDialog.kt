package com.rino.pressureandpulse.ui.dialogs

import android.widget.TimePicker
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog

@Composable
fun TimeDialog(
    initialValue: Pair<Int, Int>,
    onDismiss: () -> Unit,
    onTimeChange: (Int, Int) -> Unit
) {
    var selectedHour by remember { mutableStateOf(initialValue.first) }
    var selectedMinute by remember { mutableStateOf(initialValue.second) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AndroidView({
                    TimePicker(it).apply {
                        setIs24HourView(true)
                        hour = selectedHour
                        minute = selectedMinute
                    }
                },
                    Modifier.wrapContentSize(),
                    update = { view ->
                        view.setOnTimeChangedListener { _, hour, minute ->
                            selectedHour = hour
                            selectedMinute = minute
                        }
                    }
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(8.dp)
                ) {
                    Button(onClick = onDismiss) {
                        Text(text = "Cancel")
                    }
                    Button(onClick = { onTimeChange(selectedHour, selectedMinute) }) {
                        Text(text = "Set")
                    }
                }
            }
        }
    }
}