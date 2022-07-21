package com.picpay.desafio.android.repository

import androidx.lifecycle.MutableLiveData
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.service.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository {
    private var usersData = MutableLiveData<List<User>>()
    val users get() = usersData

    suspend fun fetchDados() {
        val result = ApiService.service.getUsers()
        withContext(Dispatchers.Main) {
            usersData.value = result
        }
    }

}