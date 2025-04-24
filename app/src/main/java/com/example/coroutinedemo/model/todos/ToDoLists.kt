package com.example.coroutinedemo.model.todos

data class ToDoLists(
    val userId:Int? =null,
    val title:String? = null,
    val completed:Boolean?= null
)
