package com.picpay.desafio.android.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertUsers(restaurants: List<User>)

    @Query("DELETE FROM users")
    suspend fun deleteAllUsers()
}