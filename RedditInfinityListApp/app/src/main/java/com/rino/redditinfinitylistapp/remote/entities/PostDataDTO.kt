package com.rino.redditinfinitylistapp.remote.entities

import com.google.gson.annotations.SerializedName

data class PostDataDTO(
    val title: String,
    val name: String,
    val score: Long,
    val thumbnail: String,
    val id: String,
    @SerializedName("num_comments")
    val comments: Long
)