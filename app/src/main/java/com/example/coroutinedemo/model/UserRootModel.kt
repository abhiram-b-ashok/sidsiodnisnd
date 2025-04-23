package com.example.coroutinedemo.model

import org.json.JSONArray

data class UserRootModel(
    val page: Int,
    val perPage: Int,
    val list: List<User>,
    val lastPage: Int = 1
)

data class User(
    val id: Int,
    var fname: String,
    var lname: String,
    var email: String,
    val image: String,
)

fun JSONArray?.parseUserData(): List<User> {
    val uList = arrayListOf<User>()
    this?.let {
        for (i in 0 until it.length()) {
            val dataObject = it.getJSONObject(i)
            val id = dataObject.getInt("id")
            val fname = dataObject.getString("first_name")
            val lname = dataObject.getString("last_name")
            val mail = dataObject.getString("email")
            val img = dataObject.getString("avatar")

            uList.add(User(id, fname, lname, mail, img))
        }
    }
    return uList
}
