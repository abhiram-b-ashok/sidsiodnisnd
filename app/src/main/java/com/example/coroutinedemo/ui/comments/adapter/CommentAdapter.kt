package com.example.coroutinedemo.ui.comments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinedemo.databinding.CommentApiRecyclerBinding
import com.example.coroutinedemo.model.comments.Comments

class CommentAdapter(private var list: List<Comments>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
       val binding = CommentApiRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
    holder.apply {
        name.text = list[position].name
        email.text = list[position].email
        body.text = list[position].body
    }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class CommentViewHolder(binding: CommentApiRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        val name = binding.nameId
        val email = binding.emailId
        val body = binding.bodyId
    }


     fun updateData(list: List<Comments>){
        this.list = list
        notifyDataSetChanged()


    }
}