package com.example.coroutinedemo.ui.main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.coroutinedemo.R
import com.example.coroutinedemo.databinding.ActivityApiGetBinding
import com.example.coroutinedemo.model.users.Users

class ApiGetActivity : AppCompatActivity() {

    private lateinit var binding: ActivityApiGetBinding
    private lateinit var navController: NavController
    val data = mutableListOf<Users>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityApiGetBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity) as NavHostFragment
        navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each

//        adapter = ApiGetAdapter(mutableListOf())
//        binding.getPostRecycler.adapter = adapter
//        userApiCall()
//        binding.getPostButton.setOnClickListener{
//            binding.getPostRecycler.visibility = View.VISIBLE
//        }

    }
//    private fun userApiCall() {
//        lifecycleScope.launch {
//            val response = viewModel.getApiRequest()
//            try {
//                val jsonArray = response.data
//                for (i in 0 until (jsonArray?.length() ?: 0)) {
//                    val item = jsonArray?.getJSONObject(i)
//                    val id = item?.getInt("userId")
//                    val title = item?.getString("title")
//                    val body = item?.getString("body")
//                    data.add(Users(id, title, body))
//                }
//                adapter.updateData(data)
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.e("Exception", "${e.message}",)
//            }
//        }
//    }
}

