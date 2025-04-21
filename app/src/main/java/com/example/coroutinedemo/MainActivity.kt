package com.example.coroutinedemo

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.coroutinedemo.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModelDemo by viewModels()

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
//       viewModel.getInput().observe(this){text->
//            binding.textView.text = text


        lifecycleScope.launch {
            val response = viewModel.getRequest("https://mocki.io/v1/09a2dd97-3a31-4047-87f6-6981a5c14566")
            withContext(Dispatchers.Main) {
                Log.d("TAG_jsonRESPONSE_1", "onCreate:$response ")
                try {

                    val datas = mutableListOf<FetchedData>()
                    val jsonObject = JSONObject(response)
                    Log.d("TAG_jsonRESPONSE_2", "onCreate: $jsonObject")
                    val jsonArray = jsonObject.getJSONArray("result")
                    Log.i("jsonArray", "$jsonArray")
                    for (i in 0 until jsonArray.length()) {
                        val dataObject = jsonArray.getJSONObject(i)
                        val name = dataObject.getString("name")
                        val price = dataObject.getInt("price")
                        val quantity = dataObject.getString("available_quanity")
                        datas.add(FetchedData(name, price, quantity))
                    }
                    Log.i("parseddata", "$datas")

                    binding.apiRecyclerView.adapter = ApiAdapter(datas)


//                    binding.textView.setText("$name,$age,$address")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }


        }


    }


}



