package com.example.kotlinroom.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Users")
data class Users(
    @PrimaryKey(autoGenerate =true)
    val id:Int,
    val isim:String,
    val age:Int
    ):Parcelable