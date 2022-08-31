package com.rino.popularmovies.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.rino.popularmovies.paging.MoviesPagingSource
import com.rino.popularmovies.repositories.MoviesRepository

class MainViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    val moviesPager = Pager(
        PagingConfig(pageSize = 10)
    ) {
        MoviesPagingSource(moviesRepository)
    }.flow.cachedIn(viewModelScope)

}