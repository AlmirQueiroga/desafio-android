package com.picpay.desafio.android.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.models.User

@Database(entities = [User::class], version = 1)
abstract class UserDataBase: RoomDatabase() {

    abstract fun userDao(): UserDao

}