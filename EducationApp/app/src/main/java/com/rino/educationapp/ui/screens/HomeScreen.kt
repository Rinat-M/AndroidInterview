package com.rino.educationapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.rino.educationapp.R
import com.rino.educationapp.ui.theme.*

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier
            .background(Grey900)
            .fillMaxSize()
    ) {
        Column {
            TopSection(name = "Rinat")
            ExamsTimerSection()
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
            .padding(15.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Green400, Color.Cyan)
                )
            )
            .padding(horizontal = 15.dp, vertical = 20.dp)
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