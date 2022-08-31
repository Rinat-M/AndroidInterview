package com.rino.popularmovies.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rino.popularmovies.remote.entites.MovieDTO
import com.rino.popularmovies.repositories.MoviesRepository

class MoviesPagingSource(
    private val moviesRepository: MoviesRepository
) : PagingSource<Int, MovieDTO>() {
    override fun getRefreshKey(state: PagingState<Int, MovieDTO>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDTO> {
        return try {
            Log.d("PAGING", "params.key - ${params.key}")
            val page = params.key ?: 1
            val moviesDTO = moviesRepository.getPopularMovies(page = page)
            LoadResult.Page(
                data = moviesDTO.results,
                prevKey = null,
                nextKey = if (moviesDTO.results.isNotEmpty()) moviesDTO.page + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}