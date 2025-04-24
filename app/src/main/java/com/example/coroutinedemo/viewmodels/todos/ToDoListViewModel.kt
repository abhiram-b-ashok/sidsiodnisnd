package com.example.coroutinedemo.viewmodels.todos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinedemo.apis.todoapi.toDoApi
import com.example.coroutinedemo.model.todos.ToDoLists
import kotlinx.coroutines.launch

class ToDoListViewModel :ViewModel() {
    private val _todoList = MutableLiveData<List<ToDoLists>>()
    val todoList: LiveData<List<ToDoLists>> = _todoList

    fun getTodoList() = viewModelScope.launch {
        val data = mutableListOf<ToDoLists>()
        val response = toDoApi()

        try {
            val jsonArray = response.data
            for (i in 0 until (jsonArray?.length() ?: 0)) {
                val item = jsonArray?.getJSONObject(i)
                val id = item?.getInt("id")
                val title = item?.getString("title")
                val completed = item?.getBoolean("completed")
                data.add(ToDoLists(id, title, completed))
            }
            _todoList.value = data
        } catch (e: Exception) {
            e.printStackTrace()

        }
    }
}