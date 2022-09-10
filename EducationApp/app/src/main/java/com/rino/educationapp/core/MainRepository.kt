package com.rino.educationapp.core

interface MainRepository {
    fun getLessons(): List<Lesson>
}