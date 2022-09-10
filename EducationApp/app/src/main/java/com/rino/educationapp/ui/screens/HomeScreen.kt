package com.rino.educationapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rino.educationapp.R
import com.rino.educationapp.core.entity.Lesson
import com.rino.educationapp.core.VerticalRotation
import com.rino.educationapp.core.rotateVertically
import com.rino.educationapp.core.background
import com.rino.educationapp.core.entity.Homework
import com.rino.educationapp.ui.theme.*

@Composable
fun HomeScreen(mainViewModel: MainViewModel) {
    Box(
        modifier = Modifier
            .background(Grey900)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            TopSection(name = "Rinat")
            ExamsTimerSection()
            ClassesSection(mainViewModel.lessons)
            HomeworksSection(mainViewModel.homeworks)
        }
    }
}

@Composable
fun TopSection(
    name: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                append("Hi, ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("$name!")
                }
            },
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
                painter = painterResource(id = R.drawable.ic_tune),
                contentDescription = "Tune",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_account_circle),
                contentDescription = "Account",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun ExamsTimerSection() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Green400, Color.Cyan)
                )
            )
            .padding(horizontal = 15.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "Are you ready for exams?",
            color = Color.White,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(4.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TwoDigitsBlock(digit = 9, title = "Days")
            Text(
                text = ":",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(PaddingValues(start = 6.dp, top = 6.dp))
                    .align(Alignment.Top)
            )
            TwoDigitsBlock(digit = 23, title = "Hours")
            Text(
                text = ":",
                fontWeight = FontWeight.Bold,
                color = Color.White,
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(PaddingValues(start = 6.dp, top = 6.dp))
                    .align(Alignment.Top)
            )
            TwoDigitsBlock(digit = 59, title = "Minutes")
        }
    }
}

@Composable
fun TwoDigitsBlock(
    digit: Int,
    title: String,
    backgroundColor: Color = BlackWithOpacity40
) {
    val digitString: String = digit.toString().padStart(2, '0')
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            OneDigit(digit = digitString[0].toString(), backgroundColor = backgroundColor)
            OneDigit(digit = digitString[1].toString(), backgroundColor = backgroundColor)
        }
        Box(contentAlignment = Alignment.Center) {
            Text(text = title, color = Color.White)
        }

    }
}

@Composable
fun OneDigit(digit: String, backgroundColor: Color) {
    Box(
        modifier = Modifier
            .padding(PaddingValues(start = 6.dp, top = 8.dp, bottom = 8.dp))
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
    ) {
        Text(
            text = digit,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(9.dp)
        )
    }
}

@Composable
fun ClassesSection(lessons: List<Lesson>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(text = "Classes", color = Color.White)
        Text(text = "${lessons.count()} classes today", color = Color.Gray)
    }
    LazyRow {
        items(lessons.count()) {
            ClassBlock(lessons[it])
        }
    }
}

@Composable
fun ClassBlock(lesson: Lesson) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(lesson.isAdditional)
            .padding(PaddingValues(start = 15.dp))
            .width(330.dp)
            .height(100.dp)
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
            Text(text = "${lesson.startTime} - ${lesson.endTime}", color = Color.Gray)
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

@Composable
fun HomeworksSection(homeworks: List<Homework>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
    ) {
        Text(text = "Homework", color = Color.White)
    }
    LazyRow {
        items(homeworks.count()) {
            HomeworkBlock(homeworks[it])
        }
    }
}

@Composable
fun HomeworkBlock(homework: Homework) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Grey800)
            .size(200.dp)
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = homework.title, color = Color.White)
                Text(
                    text = "${homework.daysLeft} days left",
                    color = Color.Gray,
                    style = MaterialTheme.typography.body2
                )
            }
            Box(
                modifier = Modifier
                    .border(2.dp, Color.Gray, CircleShape)
                    .clip(CircleShape)
                    .background(BlackWithOpacity50)
                    .padding(6.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_library_books),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }
        Text(
            text = homework.description,
            color = Color.White,
            fontSize = 12.sp
        )
        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_account_circle),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .border(2.dp, Grey800, CircleShape)
                    .size(30.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_account_circle),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .offset((-8).dp)
                    .size(30.dp)
                    .border(2.dp, Grey800, CircleShape)
            )

        }
    }
}