package com.rino.redditinfinitylistapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rino.redditinfinitylistapp.remote.entities.PostDTO
import com.rino.redditinfinitylistapp.repositories.RedditRepository

class HotPostsPagingSource(
    private val redditRepository: RedditRepository
) : PagingSource<String, PostDTO>() {

    override fun getRefreshKey(state: PagingState<String, PostDTO>): String? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            val lastPost = page?.data?.last()
            lastPost?.data?.name
        }
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, PostDTO> {
        return try {
            val responseDTO = redditRepository.getHotPosts(after = params.key, limit = 25)
            LoadResult.Page(
                data = responseDTO?.data?.children ?: listOf(),
                prevKey = null,
                nextKey = if (responseDTO?.data?.children?.isNotEmpty() == true) responseDTO.data.after else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}