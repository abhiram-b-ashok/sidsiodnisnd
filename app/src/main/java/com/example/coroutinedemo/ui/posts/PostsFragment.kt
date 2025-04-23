package com.example.coroutinedemo.ui.posts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.coroutinedemo.databinding.FragmentPostsBinding
import com.example.coroutinedemo.model.users.Users
import com.example.coroutinedemo.ui.posts.adapters.ApiGetAdapter
import com.example.coroutinedemo.viewmodels.posts.GetApiViewModel
import kotlinx.coroutines.launch

class PostsFragment : Fragment() {
    private lateinit var binding: FragmentPostsBinding
    private val viewModel: GetApiViewModel by viewModels()
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

        binding.getPostButton.setOnClickListener {
            binding.getPostRecycler.visibility = View.VISIBLE
            //findNavController().navigate(PostsFragmentDirections.actionPostsFragmentToPostDetailsFragment("url"))
            userApiCall()
        }

    }

    private fun userApiCall() {
        lifecycleScope.launch {
            val response = viewModel.getApiRequest()
            try {
                val jsonArray = response.data
                for (i in 0 until (jsonArray?.length() ?: 0)) {
                    val item = jsonArray?.getJSONObject(i)
                    val id = item?.getInt("userId")
                    val title = item?.getString("title")
                    val body = item?.getString("body")
                    data.add(Users(id, title, body))
                }
                adapter.updateData(data)
            } catch (e: Exception) {
                e.printStackTrace()
                Log.e("Exception", "${e.message}",)
            }
        }
    }
}
