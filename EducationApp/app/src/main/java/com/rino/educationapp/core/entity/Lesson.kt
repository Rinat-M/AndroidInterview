package com.rino.educationapp.core.entity

data class Lesson(
    val title: String,
    val teacher: String,
    val startTime: String,
    val endTime: String,
    val isAdditional: Boolean,
    val bySkype: Boolean
)