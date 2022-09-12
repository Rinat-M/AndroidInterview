package com.rino.educationapp.core.repositories

import com.rino.educationapp.core.entity.Homework
import com.rino.educationapp.core.entity.Lesson

interface MainRepository {
    fun getLessons(): List<Lesson>
    fun getHomeworks(): List<Homework>
}