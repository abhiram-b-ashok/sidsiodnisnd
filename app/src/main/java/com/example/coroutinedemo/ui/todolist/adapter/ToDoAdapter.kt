package com.example.coroutinedemo.ui.todolist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinedemo.databinding.TodoApiRecyclerBinding
import com.example.coroutinedemo.model.todos.ToDoLists

class ToDoAdapter(private var list: List<ToDoLists>) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = TodoApiRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.id.text = list[position].userId.toString()
        holder.title.text = list[position].title
        holder.completed.text = list[position].completed.toString()
    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ToDoViewHolder(binding: TodoApiRecyclerBinding):RecyclerView.ViewHolder(binding.root) {
        val id = binding.userId
        val title = binding.titleId
        val completed = binding.completedId
    }
    fun updateData(list: List<ToDoLists>) {
        this.list = list
        notifyDataSetChanged()
    }
}