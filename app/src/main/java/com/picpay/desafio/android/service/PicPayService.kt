package com.picpay.desafio.android.service

import com.picpay.desafio.android.repository.User
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<User>
}