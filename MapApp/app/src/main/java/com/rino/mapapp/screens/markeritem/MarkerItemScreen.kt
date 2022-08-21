package com.rino.mapapp.screens.markeritem

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rino.mapapp.screens.maps.MapsViewModel

@Composable
fun MarkerItemScreen(
    mapsViewModel: MapsViewModel = viewModel()
) {
    var title by rememberSaveable {
        mutableStateOf(mapsViewModel.getSelectedMarker()?.title ?: "")
    }
    var description by rememberSaveable {
        mutableStateOf(mapsViewModel.getSelectedMarker()?.description ?: "")
    }

    Column {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title,
            onValueChange = {
                title = it
                mapsViewModel.updateMarkerInList(title = title, description = description)
            },
            label = { Text("Title") }
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = description,
            onValueChange = {
                description = it
                mapsViewModel.updateMarkerInList(title = title, description = description)
            },
            label = { Text("Description") }
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = mapsViewModel.getSelectedMarker()?.latLng?.toString() ?: ""
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun MarkerItemScreenPreview() {
    MarkerItemScreen()
}
