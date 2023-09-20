package com.example.kotlinroom.data

import androidx.lifecycle.LiveData

class Repository(val userDAO:UserDAO) {
    val readAllData:LiveData<List<Users>> =userDAO.readAllData()

    suspend fun addData(users:Users){
        userDAO.addData(users)
    }
    suspend fun updateData(users: Users){
        userDAO.updateData(users)
    }

    suspend fun deleteData(users: Users){
        userDAO.deleteData(users)
    }
}