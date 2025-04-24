package com.example.coroutinedemo.apis.commentsapi

import org.json.JSONArray

data class CommentApiResponse(
    val code: Int?,
    val exception: Throwable?,
    val message: String?,
    val data : JSONArray? = null
)
