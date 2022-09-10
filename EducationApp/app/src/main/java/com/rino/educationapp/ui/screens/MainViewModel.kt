package com.rino.educationapp.ui.screens

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.rino.educationapp.core.MainRepository

class MainViewModel(repository: MainRepository) : ViewModel() {

    private val _lessons = repository.getLessons().toMutableStateList()
    val lessons get() = _lessons

}