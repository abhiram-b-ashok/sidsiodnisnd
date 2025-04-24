package com.example.coroutinedemo.apis.todoapi

import org.json.JSONArray

data class ToDoApiResponse(
    val code: Int?,
    val exception: Throwable?,
    val message: String?,
    val data : JSONArray? = null
)
