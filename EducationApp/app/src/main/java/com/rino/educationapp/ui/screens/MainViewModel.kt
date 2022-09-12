package com.rino.educationapp.ui.screens

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.rino.educationapp.core.repositories.MainRepository

class MainViewModel(mainRepository: MainRepository) : ViewModel() {

    private val _lessons = mainRepository.getLessons().toMutableStateList()
    val lessons get() = _lessons

    private val _homeworks = mainRepository.getHomeworks().toMutableStateList()
    val homeworks get() = _homeworks

}