package com.rino.redditinfinitylistapp.repositories

import com.rino.redditinfinitylistapp.remote.RedditService
import com.rino.redditinfinitylistapp.remote.entities.ResponseDTO

class RedditRepositoryImpl(
    private val redditService: RedditService
) : RedditRepository {

    override suspend fun getHotPosts(after: String?, limit: Int): ResponseDTO? {
        val response = redditService.getHotPosts(after, limit)
        return response.body()
    }

}