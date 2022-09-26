package com.rino.pressureandpulse.ui.dialogs

import android.widget.CalendarView
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog

@Composable
fun CalendarDialog(
    onDismiss: () -> Unit,
    onDateChange: (Int, Int, Int) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card(
            elevation = 8.dp,
            shape = RoundedCornerShape(12.dp),
        ) {
            AndroidView({ CalendarView(it) },
                Modifier.wrapContentSize(),
                update = { view ->
                    view.setOnDateChangeListener { _, year, month, dayOfMonth ->
                        onDateChange(year, month, dayOfMonth)
                    }
                }
            )
        }
    }
}