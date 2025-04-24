package com.example.coroutinedemo.ui.todolist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.coroutinedemo.R
import com.example.coroutinedemo.databinding.FragmentToDoBinding
import com.example.coroutinedemo.ui.todolist.adapter.ToDoAdapter
import com.example.coroutinedemo.utils.hide
import com.example.coroutinedemo.viewmodels.todos.ToDoListViewModel


class ToDoFragment : Fragment() {
    private lateinit var binding: FragmentToDoBinding
    private lateinit var adapter: ToDoAdapter
    private  val viewModel: ToDoListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentToDoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ToDoAdapter(mutableListOf())
        binding.toDoRecycler.adapter = adapter
        todoLiveDataObserver()

    }

    override fun onResume() {
        super.onResume()
        viewModel.getTodoList()
    }

    private fun todoLiveDataObserver() {
        viewModel.todoList.observe(viewLifecycleOwner) {
            binding.progressBar.hide()
            adapter.updateData(it)
        }


    }
}