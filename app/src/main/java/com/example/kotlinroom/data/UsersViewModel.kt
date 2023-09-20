package com.example.kotlinroom.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UsersViewModel(application:Application):AndroidViewModel(application) {
    var readAllData:LiveData<List<Users>>?=null
   private lateinit var repository:Repository

    init {
        val userDao= UsersDatabase.getDatabase(application)?.userDao()
        if(userDao !=null){
            repository=Repository(userDao)
            readAllData=userDao.readAllData()
        }
    }
    fun addData(users: Users){
        viewModelScope.launch {
            repository.addData(users)

        }
    }

    fun updateData(users:Users){
        viewModelScope.launch {
            repository.updateData(users)
        }
    }
    fun deleteData(users:Users){
        viewModelScope.launch {
            repository.deleteData(users)
        }
    }
}