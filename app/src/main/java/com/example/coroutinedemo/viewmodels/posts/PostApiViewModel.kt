package com.example.coroutinedemo.viewmodels.posts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinedemo.apis.getApiRequest
import com.example.coroutinedemo.model.users.Users
import kotlinx.coroutines.launch

class PostApiViewModel : ViewModel() {

    private val _userListData:MutableLiveData<List<Users>> = MutableLiveData()
    val userListData: LiveData<List<Users>> = _userListData


    fun userListData() = viewModelScope.launch{
        val data = mutableListOf<Users>()
        val response = getApiRequest()
        try {
            val jsonArray = response.data
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val item = jsonArray?.getJSONObject(i)
                val id = item?.getInt("userId")
                val title = item?.getString("title")
                val body = item?.getString("body")
                data.add(Users(id, title, body))
            }
            _userListData.value = data
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Exception", "${e.message}",)
        }
    }
}
