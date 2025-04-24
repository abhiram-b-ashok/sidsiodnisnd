package com.example.coroutinedemo.ui.post_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.coroutinedemo.apis.postdetailsapis.getDetailsApi
import com.example.coroutinedemo.databinding.FragmentPostDetailsBinding
import com.example.coroutinedemo.utils.hide
import kotlinx.coroutines.launch


class PostDetailsFragment : Fragment() {

private lateinit var binding: FragmentPostDetailsBinding
private val args: PostDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostDetailsBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       lifecycleScope.launch {
           val pageNo = args.pageId
           val response = getDetailsApi(pageNo)
           binding.progressBar.hide()
           binding.userId.text = response.data?.getString("id")
           binding.title.text = response.data?.getString("title")
           binding.bodyId.text = response.data?.getString("body")

       }
    }

}