package com.example.coroutinedemo

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinedemo.databinding.ActivityMainBinding
import com.example.coroutinedemo.model.User
import com.example.coroutinedemo.model.UserRootModel
import com.example.coroutinedemo.model.parseUserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModelDemo by viewModels()
    val list = mutableListOf<User>()
    var currentPage = 1
    var lastPage = 1
//    private var page = 1
//    private val limit = 2
//    private var isLoading = false
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
        val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
        insets
    }



//        val textObserver = Observer<String> { text ->
//            binding.textView.text = text
//        }
//        viewModel.getInput().observe(this, textObserver)
 //   viewModel.getInput().observe(this) { text ->
        //  binding.textView.text = text


//        lifecycleScope.launch {
//
//            withContext(Dispatchers.Main) {
//                val response =
//                    viewModel.getRequest("https://mocki.io/v1/09a2dd97-3a31-4047-87f6-6981a5c14566")
//                Log.d("TAG_jsonRESPONSE_1", "onCreate:$response ")
//                try {
//
//                    val datas = mutableListOf<FetchedData>()
//                    val jsonObject = JSONObject(response)
//                    Log.d("TAG_jsonRESPONSE_2", "onCreate: $jsonObject")
//                    val jsonArray = jsonObject.getJSONArray("result")
//                    Log.i("jsonArray", "$jsonArray")
//                    for (i in 0 until jsonArray.length()) {
//                        val dataObject = jsonArray.getJSONObject(i)
//                        val name = dataObject.getString("name")
//                        val price = dataObject.getInt("price")
//                        val quantity = dataObject.getString("available_quanity")
//                        datas.add(FetchedData(name, price, quantity))
//                    }
//                    Log.i("parseddata", "$datas")
//                    binding.apiRecyclerView.adapter = ApiAdapter(datas)
//
//
////                    binding.textView.setText("$name,$age,$address")
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                }
//            }
//
//
//        }
   // }

    binding.userApiRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            if (!recyclerView.canScrollVertically(1) && dy > 0)
            {
                Toast.makeText(application,"bottom", Toast.LENGTH_LONG).show()
            }else if (!recyclerView.canScrollVertically(-1) && dy < 0)
            {
                Toast.makeText(application,"top", Toast.LENGTH_LONG).show()
            }
        }
    })

    binding.btnSeeMore.setOnClickListener {
        if (currentPage <= lastPage){
            currentPage++
            apiCall()
        }
    }
      apiCall()
    }

    fun apiCall(){
        lifecycleScope.launch {
            val response = viewModel.getRequest2(currentPage)
            Log.e("response","<<<<<<< $response")
            try {
                val rootModel = UserRootModel(
                    page = response.data?.getInt("page") ?: 0,
                    perPage = response.data?.getInt("per_page") ?: 0,
                    list = response.data?.getJSONArray("data")?.parseUserData() ?: arrayListOf(),
                    lastPage = response.data?.getInt("total_pages") ?: 1
                )
                lastPage = rootModel.lastPage
                list.addAll(rootModel.list)
                binding.userApiRecycler.adapter = UserApiAdapter(list.toList())

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}





