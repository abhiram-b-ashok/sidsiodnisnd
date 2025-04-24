package com.example.coroutinedemo.apis.postdetailsapis

import org.json.JSONObject

data class DetailApiResponse(
    val code: Int?,
    val exception: Throwable?,
    val message: String?,
    val data : JSONObject? = null
)
