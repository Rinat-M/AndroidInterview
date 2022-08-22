package com.rino.mapapp.screens.markers

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.android.gms.maps.model.LatLng
import com.rino.mapapp.base.MapMarker
import com.rino.mapapp.screens.maps.MapsViewModel

@Composable
fun MarkersScreen(
    mapsViewModel: MapsViewModel = viewModel(),
    onNavigateToMarkerItem: () -> Unit
) {
    MarkersList(
        markers = mapsViewModel.markers,
        onDeleteClick = { marker -> mapsViewModel.remove(marker) },
        onMarkerClick = { marker ->
            mapsViewModel.setSelectedMarker(marker)
            onNavigateToMarkerItem()
        }
    )
}

@Composable
private fun MarkersList(
    markers: List<MapMarker>,
    onDeleteClick: (marker: MapMarker) -> Unit,
    onMarkerClick: (marker: MapMarker) -> Unit
) {
    LazyColumn {
        items(markers) { marker ->
            MarkerItem(
                marker = marker,
                onDelete = { onDeleteClick(marker) },
                onClick = { onMarkerClick(marker) }
            )
        }
    }
}

@Composable
private fun MarkerItem(
    marker: MapMarker,
    modifier: Modifier = Modifier,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .padding(8.dp)
                .weight(1f)
                .clickable { onClick() }
        ) {
            Text(text = marker.title, fontWeight = FontWeight.Bold)
            Text(text = marker.description, fontStyle = FontStyle.Italic)
            Text(text = marker.latLng.toString())
            Divider(modifier = Modifier.padding(top = 8.dp))
        }
        IconButton(modifier = modifier.wrapContentSize(), onClick = { onDelete() }) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DefaultPreview() {
    MarkerItem(
        marker = MapMarker(
            LatLng(55.751244, 37.618423),
            "Moscow",
            "Marker in Moscow"
        ),
        onDelete = {},
        onClick = {}
    )
}