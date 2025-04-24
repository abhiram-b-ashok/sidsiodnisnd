package com.example.coroutinedemo.ui.posts.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinedemo.databinding.GetApiRecyclerLayoutBinding
import com.example.coroutinedemo.model.users.Users

class ApiGetAdapter(private var list: List<Users>):RecyclerView.Adapter<ApiGetAdapter.ApiGetViewHolder>() {
    var onItemClick: ((Int, Users) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiGetViewHolder {
        val binding = GetApiRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ApiGetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApiGetViewHolder, position: Int) {
        holder.bind(list[position], onItemClick)

    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ApiGetViewHolder(binding: GetApiRecyclerLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        private val uid = binding.userId
        val title = binding.titleId
        val body = binding.bodyId

        fun bind(item: Users, onItemClick: ((Int, Users) -> Unit)?) {
            uid.text = item.userId.toString()
            title.text = item.title
            body.text = item.body
            itemView.setOnClickListener {
                onItemClick?.invoke(adapterPosition, item)
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: List<Users>){
        this.list = list
        notifyDataSetChanged()
    }
}