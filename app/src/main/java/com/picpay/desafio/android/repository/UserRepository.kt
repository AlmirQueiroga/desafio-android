package com.picpay.desafio.android.repository

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.room.withTransaction
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    private val userDb = ApiService.userDataBase
    private val userDao = ApiService.userDataBase.userDao()
    val userData = ApiService.userDataBase.userDao().getAllUsers().asLiveData()

    suspend fun fetchDados() {
        val result = ApiService.service.getUsers()
        userDb.withTransaction {
            userDao.deleteAllUsers()
            userDao.insertUsers(result)
        }
    }

}