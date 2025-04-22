package com.example.coroutinedemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coroutinedemo.databinding.UserApiRecyclerLayoutBinding
import com.example.coroutinedemo.model.User

class UserApiAdapter(private val userList:List<User>):RecyclerView.Adapter<UserApiAdapter.UserApiViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserApiViewHolder {
        val binding = UserApiRecyclerLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserApiViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserApiViewHolder, position: Int) {
        Glide.with(holder.img)
            .load(userList[position].image)
            .placeholder(R.drawable.baseline_account_circle_24)
            .error(R.drawable.baseline_account_circle_24)
            .circleCrop()
            .into(holder.img)

        holder.email.text = userList[position].email
        holder.name.setText(userList[position].fname + " " +userList[position].lname)

    }

    override fun getItemCount(): Int {
       return userList.size
    }

    class UserApiViewHolder ( binding: UserApiRecyclerLayoutBinding):RecyclerView.ViewHolder(binding.root){

        val img = binding.userImg
        val name = binding.userName
        val email = binding.userMail

    }
}