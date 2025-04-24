package com.example.coroutinedemo.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

fun View.show(){
    this.visibility = View.VISIBLE
}

fun View.hide(){
    this.visibility = View.GONE
}

fun View.invisible(){
    this.visibility = View.INVISIBLE
}

fun Fragment.toast(msg: String){
    Toast.makeText(requireContext(),msg,Toast.LENGTH_SHORT).show()
}