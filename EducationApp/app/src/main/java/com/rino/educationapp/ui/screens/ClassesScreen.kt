package com.rino.educationapp.ui.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rino.educationapp.R
import com.rino.educationapp.core.VerticalRotation
import com.rino.educationapp.core.background
import com.rino.educationapp.core.entity.Lesson
import com.rino.educationapp.core.rotateVertically
import com.rino.educationapp.ui.theme.BlackWithOpacity50
import com.rino.educationapp.ui.theme.Blue200
import com.rino.educationapp.ui.theme.Grey900

@Composable
fun ClassesScreen(mainViewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .background(Grey900)
            .fillMaxSize()
    ) {
        Timeline()
        Column {
            TopSection()
            ClassesOnTimelineSection(mainViewModel.lessons)
        }
    }
}

@Composable
fun TopSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = "Classes",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h5,
            color = Color.White
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth(0.55f)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_format_list_bulleted),
                contentDescription = "List",
                tint = Color.Green,
                modifier = Modifier.size(30.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_grid_view),
                contentDescription = "Grid",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun Timeline() {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, top = 64.dp)
    ) {
        val canvasHeight = size.height
        drawLine(
            start = Offset(x = 100f, y = 0f),
            end = Offset(x = 100f, y = canvasHeight),
            color = Color.Green,
            strokeWidth = 5F
        )
    }
}

@Composable
fun ClassesOnTimelineSection(lessons: List<Lesson>) {
    LazyColumn {
        items(lessons.size) { index ->
            ClassOnTimelineBlock(lessons[index], index == 0)
        }
    }
}

@Composable
fun ClassOnTimelineBlock(lesson: Lesson, isNow: Boolean = false) {
    if (isNow) {
        DotOnTimelineNow()
    } else {
        DotOnTimeline()
    }

    Column(
        modifier = Modifier
            .padding(start = 75.dp, end = 15.dp, bottom = 8.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "${lesson.startTime} - ${lesson.endTime}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        ClassCard(lesson)
    }
}

@Composable
fun DotOnTimeline(color: Color = Color.Green) {
    Canvas(
        modifier = Modifier
            .offset(44.dp, 26.dp)
    ) {
        drawCircle(color = color, radius = 14f)
    }
}

@Composable
fun DotOnTimelineNow(outerColor: Color = Color.Green, innerColor: Color = Color.White) {
    Canvas(
        modifier = Modifier
            .offset(44.dp, 26.dp)
    ) {
        drawCircle(color = outerColor, radius = 32f)
        drawCircle(color = innerColor, radius = 14f)
    }
}

@Composable
fun ClassCard(lesson: Lesson) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(24.dp))
            .background(lesson.isAdditional)
            .padding(start = 15.dp)
            .height(100.dp)
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .border(2.dp, Color.Gray, CircleShape)
                .clip(CircleShape)
                .background(BlackWithOpacity50)
                .padding(6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_book),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(30.dp)
                    .padding(4.dp)
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(text = lesson.title, color = Color.White)
            Text(text = "Teacher: ${lesson.teacher}", color = Color.Gray)
        }

        if (lesson.bySkype) {
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .fillMaxHeight()
                    .background(Blue200),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = "Open in S", color = Color.White,
                    modifier = Modifier
                        .rotateVertically(VerticalRotation.CLOCKWISE),
                    fontSize = 10.sp,
                )
            }
        }
    }
}
