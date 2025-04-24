package com.example.coroutinedemo.apis.commentsapi

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

suspend fun getCommentsApi(): CommentApiResponse = withContext(IO)
{
    var code = 0
    var exception: Throwable? = null
    val message: String? = null
    var data: JSONArray? = null

    try {
        val url = HttpUrl.Builder()
            .scheme("https")
            .host("jsonplaceholder.typicode.com")
            .addPathSegment("comments")
            .build()

        val request = Request.Builder()
            .url(url)
            .build()
        val httpClient = OkHttpClient()
            .newBuilder()
            .build()

        val response = httpClient.newCall(request).execute()
        code = response.code
        data = response.body?.string()?.let {
            JSONArray(it)
        }

    }
    catch (ex:Exception)
    {
        exception = ex
    }
    return@withContext CommentApiResponse(code, exception, message, data)

}