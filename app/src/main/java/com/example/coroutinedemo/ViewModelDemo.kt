package com.example.coroutinedemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request

class ViewModelDemo :ViewModel() {
    private val inputs:MutableLiveData<String> = MutableLiveData()

    fun getInput():LiveData<String>{
        return  inputs
    }
    fun setInput(ip:String) {
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
    suspend fun getRequest2(url: String): String = withContext(IO) {
        val httpClient = OkHttpClient()
        val request = Request.Builder()
            .url(url).get()
            .build()

        val response = httpClient.newCall(request).execute()

        return@withContext response.body?.string() ?: ""

    }

}