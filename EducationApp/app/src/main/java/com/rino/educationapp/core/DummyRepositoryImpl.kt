package com.rino.educationapp.core

class DummyRepositoryImpl() : MainRepository {
    override fun getLessons(): List<Lesson> {
        return listOf(
            Lesson(
                title = "History",
                teacher = "Mrs Thomas",
                startTime = "8:00",
                endTime = "8:45",
                isAdditional = false,
                bySkype = true
            ),
            Lesson(
                title = "Literature",
                teacher = "Mrs Barros",
                startTime = "9:00",
                endTime = "9:45",
                isAdditional = false,
                bySkype = false
            ),
            Lesson(
                title = "Physical Education",
                teacher = "Mrs Barros",
                startTime = "10:00",
                endTime = "11:35",
                isAdditional = true,
                bySkype = false
            )
        )
    }
}