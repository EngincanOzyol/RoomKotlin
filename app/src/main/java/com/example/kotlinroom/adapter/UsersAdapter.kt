package com.example.kotlinroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinroom.R
import com.example.kotlinroom.data.Users
import com.example.kotlinroom.databinding.RecylerviewRowBinding
import com.example.kotlinroom.fragment.ListFragmentDirections

class UsersAdapter():RecyclerView.Adapter<UsersAdapter.UsersHolder>() {
    var usersList= emptyList<Users>()
    class UsersHolder(itemView:View):RecyclerView.ViewHolder(itemView){
      val binding=RecylerviewRowBinding.bind(itemView)
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersHolder {
        val inflate=LayoutInflater.from(parent.context)
         val view=inflate.inflate(R.layout.recylerview_row,parent,false)
         return UsersHolder(view)
     }

     override fun onBindViewHolder(holder: UsersHolder, position: Int) {
         holder.binding.textName.text=usersList[position].isim
         holder.binding.textAge.text= usersList[position].age.toString()
         holder.itemView.setOnClickListener {
             val action=ListFragmentDirections.actionListFragmentToUpdateFragment(usersList[position])
             holder.itemView.findNavController().navigate(action)
         }
     }

     override fun getItemCount(): Int {
        return usersList.size
     }
    fun setData(userList: List<Users>){
        this.usersList=userList
        notifyDataSetChanged()

    }
 }