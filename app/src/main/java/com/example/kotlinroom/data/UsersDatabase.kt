package com.example.kotlinroom.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

annotation class Volatile
inline fun <R> synchronized(lock: Any, block: () -> R) {}
@Database(entities = [Users::class], version = 1, exportSchema = false)
abstract class UsersDatabase:RoomDatabase() {
    abstract fun userDao():UserDAO


    companion object{
     @Volatile
     private val INSTANCE:UsersDatabase?=null
        @OptIn(InternalCoroutinesApi::class)
        fun getDatabase(context: Context): UsersDatabase? {
            var tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance=Room.databaseBuilder<UsersDatabase>(context,UsersDatabase::class.java,"users").build()
                tempInstance=instance

                return tempInstance



            }

        }
    }


}