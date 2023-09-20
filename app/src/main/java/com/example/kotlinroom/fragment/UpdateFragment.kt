package com.example.kotlinroom.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlinroom.R
import com.example.kotlinroom.data.Users
import com.example.kotlinroom.data.UsersViewModel
import com.example.kotlinroom.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {

private lateinit var binding:FragmentUpdateBinding
private lateinit var viewModel:UsersViewModel
private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentUpdateBinding.inflate(inflater,container,false)

        viewModel=ViewModelProvider(this).get(UsersViewModel::class.java)
        binding.editName.setText(args.currentUser.isim)
        binding.editAge.setText(args.currentUser.age.toString())
        binding.guncelleBtn.setOnClickListener {
            val updateUsers=Users(args.currentUser.id,binding.editName.text.toString(),binding.editAge.text.toString().toInt())
viewModel.updateData(updateUsers)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val menuHost: MenuHost = requireActivity()

        menuHost.addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.menu_item, menu)
            }
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {

                if(menuItem.itemId==R.id.deleteItem){
                    val builder=AlertDialog.Builder(requireContext())
                    builder.setTitle("${args.currentUser.isim} kullanıcı silinecek mi?")
                    builder.setMessage("${args.currentUser.isim} kullanıcısın silmek istedğinize emin miisniz?")

                    builder.setPositiveButton("evet"){ _, _->
                        viewModel.deleteData(args.currentUser)
                        findNavController().navigate(R.id.action_updateFragment_to_listFragment)

                    }
                    builder.setNegativeButton("hayır"){
                            _,_ ->
                    }
                    builder.create().show()
                }
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
        super.onViewCreated(view, savedInstanceState)
    }



}
