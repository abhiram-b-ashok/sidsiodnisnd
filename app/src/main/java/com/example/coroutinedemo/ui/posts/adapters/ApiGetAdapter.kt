package com.example.coroutinedemo.ui.posts.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinedemo.databinding.GetApiRecyclerLayoutBinding
import com.example.coroutinedemo.model.users.Users

class ApiGetAdapter(private var list: List<Users>):RecyclerView.Adapter<ApiGetAdapter.ApiGetViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiGetViewHolder {
        val binding = GetApiRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ApiGetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApiGetViewHolder, position: Int) {
        holder.uid.text = list[position].userId.toString()
        holder.title.text = list[position].title
        holder.body.text = list[position].body

    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ApiGetViewHolder(binding: GetApiRecyclerLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        val uid = binding.userId
        val title = binding.titleId
        val body = binding.bodyId
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: List<Users>){
        this.list = list
        notifyDataSetChanged()
    }
}