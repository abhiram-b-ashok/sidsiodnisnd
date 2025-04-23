package com.example.coroutinedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject


class ViewModelDemo : ViewModel() {
    private val inputs: MutableLiveData<String> = MutableLiveData()

    fun getInput(): LiveData<String> {
        return inputs
    }

    fun setInput(ip: String) {
        inputs.value = ip
    }

    suspend fun getRequest(url: String): String = withContext(IO) {
        val httpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url).get()
            .build()

        val response = httpClient.newCall(request).execute()

        return@withContext response.body?.string() ?: ""

    }

    suspend fun getRequest2(
        pageCount: Int = 1
    ): ApiResponse = withContext(IO) {
      //  val url = "https://reqres.in/api/users"
        var exception: Throwable? = null
        var msg: String? = null
        var json: JSONObject? = null
        var code = 0

        try {

          //Create Http Url
          val url = HttpUrl.Builder()
              .scheme("https")
              .host("reqres.in")
              .addPathSegment("api")
              .addPathSegment("users")
              .addQueryParameter("page",pageCount.toString())
              .build()

            //Create Request
            val request = Request
                .Builder()
                .url(url)
                .get()
                .build()

            //Create Instance of OkHttpClient
            val httpClient = OkHttpClient()
                .newBuilder()
                //.addInterceptor(HttpLoggingInterceptor())
                .build()

            //Get Response  Calling Request Using OkHttpClient
            val response = httpClient.newCall(
                request).execute()

            code = response.code
            msg = response.message
            json = response.body?.string()?.let { JSONObject(it) }

        } catch (ex: Exception) {
            exception = ex
        }
        return@withContext ApiResponse(
            code, msg, json, exception
        )
    }
}


data class ApiResponse(
    val code: Int?,
    val massage: String?,
    val data: JSONObject?,
    val exception: Throwable?
)