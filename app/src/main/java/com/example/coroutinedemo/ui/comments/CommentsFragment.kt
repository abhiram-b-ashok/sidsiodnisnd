package com.example.coroutinedemo.ui.comments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.coroutinedemo.R
import com.example.coroutinedemo.databinding.FragmentCommentsBinding
import com.example.coroutinedemo.ui.comments.adapter.CommentAdapter
import com.example.coroutinedemo.utils.hide
import com.example.coroutinedemo.utils.toast
import com.example.coroutinedemo.viewmodels.comments.CommentsViewModel


class CommentsFragment : Fragment() {
  private lateinit var binding: FragmentCommentsBinding
    private lateinit var adapter: CommentAdapter
    private val viewModel: CommentsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCommentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CommentAdapter(mutableListOf())
        binding.commentsRecycler.adapter = adapter
        commentsLiveDataObserver()
    }

    override fun onStart() {
        super.onStart()
        toast("onStart")
    }


    override fun onResume() {
        super.onResume()
        toast("onResume")
        viewModel.fetchComments()
    }
    private fun commentsLiveDataObserver() {
        viewModel.commentsList.observe(viewLifecycleOwner){
            binding.progressBar.hide()
            adapter.updateData(it)
        }
    }

    override fun onPause() {
        super.onPause()
        toast("onPause")
    }

}