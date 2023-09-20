package com.example.kotlinroom.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {

   @Query("SELECT *FROM Users ORDER BY id ASC")
   fun readAllData():LiveData<List<Users>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addData(users: Users)

    @Update
    suspend fun updateData(users: Users)

    @Delete
    suspend fun deleteData(users: Users)

}