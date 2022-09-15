package com.rino.redditinfinitylistapp.remote

import com.rino.redditinfinitylistapp.remote.entities.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RedditService {

    @GET("r/aww/hot.json")
    suspend fun getHotPosts(
        @Query("after") after: String,
        @Query("limit") limit: Int = 25
    ): Response<ResponseDTO>

}