package com.example.coroutinedemo.apis.postapis

import org.json.JSONArray

data class ApiResponse(
    val code: Int?,
    val exception: Throwable?,
    val message: String?,
    val data : JSONArray? = null
)
