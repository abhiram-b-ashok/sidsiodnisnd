package com.example.coroutinedemo.ui.posts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.coroutinedemo.databinding.FragmentPostsBinding
import com.example.coroutinedemo.model.users.Users
import com.example.coroutinedemo.ui.posts.adapters.ApiGetAdapter
import com.example.coroutinedemo.viewmodels.posts.PostApiViewModel

class PostsFragment : Fragment() {
    private lateinit var binding: FragmentPostsBinding
    private val viewModel: PostApiViewModel by viewModels()
    private lateinit var adapter: ApiGetAdapter
    val data = mutableListOf<Users>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ApiGetAdapter(mutableListOf())
        binding.getPostRecycler.adapter = adapter
        userLiveDataObserver()
    }

    override fun onResume() {
        super.onResume()
        viewModel.userListData()
    }

    private fun userLiveDataObserver() {
      viewModel.userListData.observe(viewLifecycleOwner){
          Log.e("data_in_view","<<<<<< $it")
          adapter.updateData(it)
      }
    }
}
