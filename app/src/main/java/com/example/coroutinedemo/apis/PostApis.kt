package com.example.coroutinedemo.apis

import android.util.Log
import com.example.coroutinedemo.model.ApiResponse
import com.example.coroutinedemo.utils.HOST
import com.example.coroutinedemo.utils.SCHEME
import com.example.coroutinedemo.utils.USER_PATH_SEGMENT
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

suspend fun getApiRequest(): ApiResponse = withContext(IO) {

    // 200 - success
    // 404 - not found
    // 500 - internal server error
    // 401 - UnAuthorized Access

    var code = 0
    var exception: Throwable? = null
    val message: String? = null
    var data : JSONArray? = null

    try {

        val url = HttpUrl.Builder()
            .scheme(SCHEME)
            .host(HOST)
            .addPathSegment(USER_PATH_SEGMENT)
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
        Log.e("response_code","<<<<< $code")
        data = response.body?.string()?.let {
            JSONArray(it)
        }
    } catch (ex:Exception) {
        exception = ex
    }
    return@withContext ApiResponse(code, exception, message,data)
}