package com.rino.redditinfinitylistapp.repositories

import com.rino.redditinfinitylistapp.remote.entities.ResponseDTO

interface RedditRepository {
    suspend fun getHotPosts(after: String, limit: Int): ResponseDTO?
}