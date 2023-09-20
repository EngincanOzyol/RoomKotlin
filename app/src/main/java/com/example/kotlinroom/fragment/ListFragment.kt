package com.example.kotlinroom.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinroom.R
import com.example.kotlinroom.adapter.UsersAdapter
import com.example.kotlinroom.data.UsersViewModel
import com.example.kotlinroom.databinding.FragmentListBinding


class ListFragment : Fragment() {
   private lateinit var binding: FragmentListBinding
   private lateinit var adapter:UsersAdapter
   private lateinit var viewModel:UsersViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding= FragmentListBinding.inflate(inflater,container,false)
        val view=binding.root
        viewModel=ViewModelProvider(this).get(UsersViewModel::class.java)
        binding.recylerView.layoutManager=LinearLayoutManager(context)
        adapter=UsersAdapter()
        binding.recylerView.adapter=adapter
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }


        viewModel.readAllData?.observe(viewLifecycleOwner, Observer {
                userList->
            adapter.setData(userList)
            if(userList.isEmpty()){
                Toast.makeText(context,"liste boş",Toast.LENGTH_LONG).show()
            }
        })


        return view

    }




}