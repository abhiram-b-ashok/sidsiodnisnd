package com.example.coroutinedemo.demo_app_api

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinedemo.databinding.RecyclerViewForApiBinding

class ApiAdapter(private val list: List<FetchedData>) :RecyclerView.Adapter<ApiAdapter.ApiViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiViewHolder {
       val binding = RecyclerViewForApiBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ApiViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ApiViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.price.text = list[position].price.toString()
        holder.quantity.text = list[position].quantity

    }

    override fun getItemCount(): Int {
        return list.size
    }
    class ApiViewHolder(private val binding: RecyclerViewForApiBinding):RecyclerView.ViewHolder(binding.root) {
        val name = binding.valName
        val price = binding.valPrice
        val quantity = binding.valQuantity

    }
}