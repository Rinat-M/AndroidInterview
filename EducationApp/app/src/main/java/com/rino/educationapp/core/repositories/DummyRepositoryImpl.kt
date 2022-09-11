package com.rino.educationapp.core.repositories

import com.rino.educationapp.core.entity.Homework
import com.rino.educationapp.core.entity.Lesson

class DummyRepositoryImpl : MainRepository {
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
            ),
            Lesson(
                title = "History",
                teacher = "Mrs Thomas",
                startTime = "12:00",
                endTime = "12:45",
                isAdditional = false,
                bySkype = true
            ),
            Lesson(
                title = "Literature",
                teacher = "Mrs Barros",
                startTime = "14:00",
                endTime = "14:45",
                isAdditional = false,
                bySkype = false
            ),
        )
    }

    override fun getHomeworks(): List<Homework> {
        return listOf(
            Homework(
                title = "Literature",
                description = "Read scenes 1.1-1.12 of The Master and Margarita",
                daysLeft = 2
            ),
            Homework(
                title = "Physics",
                description = "Learn Newton's laws",
                daysLeft = 5
            ),
            Homework(
                title = "History",
                description = "Learn Greek history",
                daysLeft = 6
            ),
        )
    }
}