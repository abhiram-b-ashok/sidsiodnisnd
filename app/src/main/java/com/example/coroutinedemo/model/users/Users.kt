package com.example.coroutinedemo.model.users

data class Users(
    val userId:Int ?=null,
    val title: String ?=null,
    val body: String ?=null,
)

//fun JSONArray?.setData() : List<Users>
//{
//    val userList = arrayListOf<Users>()
//    this?.let {
//        for (i in 0 until it.length()){
//            val item = it.getJSONObject(i)
//            val id = item.getInt("userId")
//            val title = item.getString("title")
//            val body = item.getString("body")
//            userList.add(Users(id,title,body))
//        }
//    }
//        return userList
//}
