package com.rino.redditinfinitylistapp.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.rino.redditinfinitylistapp.paging.HotPostsPagingSource
import com.rino.redditinfinitylistapp.repositories.RedditRepository

class MainViewModel(
    private val redditRepository: RedditRepository
) : ViewModel() {

    val hotPostsPager = Pager(
        PagingConfig(pageSize = 25)
    ) {
        HotPostsPagingSource(redditRepository)
    }.flow.cachedIn(viewModelScope)

}