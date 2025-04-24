package com.example.coroutinedemo.viewmodels.comments

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinedemo.apis.commentsapi.getCommentsApi
import com.example.coroutinedemo.model.comments.Comments
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel() {
    private val _commentsList: MutableLiveData<List<Comments>> = MutableLiveData()
    val commentsList: LiveData<List<Comments>> = _commentsList



    fun fetchComments() = viewModelScope.launch {
        val data = mutableListOf<Comments>()
        val response = getCommentsApi()
        try {
            val jsonArray = response.data
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val item = jsonArray?.getJSONObject(i)
                val id = item?.getInt("id")
                val name = item?.getString("name")
                val email = item?.getString("email")
                val body = item?.getString("body")
                data.add(Comments(id,name,email,body))
            }
            _commentsList.value = data
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }
}