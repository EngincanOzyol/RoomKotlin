package com.example.kotlinroom.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.kotlinroom.R
import com.example.kotlinroom.data.Users
import com.example.kotlinroom.data.UsersViewModel
import com.example.kotlinroom.databinding.FragmentAddBinding


class AddFragment : Fragment() {

   private lateinit var binding: FragmentAddBinding
   private lateinit var viewModel:UsersViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentAddBinding.inflate(inflater,container,false)
        val view=binding.root

        viewModel=ViewModelProvider(this).get(UsersViewModel::class.java)
        binding.ekleBtn.setOnClickListener {
            val users=Users(0,binding.editName.text.toString(),binding.editAge.text.toString().toInt())
            viewModel.addData(users)
            Toast.makeText(context,"kullanıcı eklendi",Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)

        }



        return view
    }


}