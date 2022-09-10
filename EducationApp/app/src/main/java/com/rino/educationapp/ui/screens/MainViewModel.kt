package com.rino.educationapp.ui.screens

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.rino.educationapp.core.repositories.MainRepository

class MainViewModel(repository: MainRepository) : ViewModel() {

    private val _lessons = repository.getLessons().toMutableStateList()
    val lessons get() = _lessons

    private val _homeworks = repository.getHomeworks().toMutableStateList()
    val homeworks get() = _homeworks

}