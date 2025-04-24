package com.example.coroutinedemo.apis.postdetailsapis

import android.util.Log
import com.example.coroutinedemo.apis.postapis.ApiResponse
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

suspend fun getDetailsApi(pagenum: Int): DetailApiResponse = withContext(IO) {
    var code = 0
    var exception: Throwable? = null
    val message: String? = null
    var data: JSONObject? = null

    try {
        val url = HttpUrl.Builder()
            .scheme("https")
            .host("jsonplaceholder.typicode.com")
            .addPathSegment("posts")
            .addPathSegment("$pagenum")
            .build()


        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        val httpClient = OkHttpClient()
            .newBuilder()
            .build()

        val response = httpClient.newCall(request).execute()
        code = response.code
        data = response.body?.string()?.let {
            JSONObject(it)
        }

    } catch (ex: Exception) {
        exception = ex
    }

    return@withContext DetailApiResponse(code, exception, message, data)

}