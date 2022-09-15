package com.rino.redditinfinitylistapp.remote.entities

data class DataDTO(
    val after: String,
    val dist: Long,
    val children: List<PostDTO>,
    val before: String?
)