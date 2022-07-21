package com.picpay.desafio.android.service

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {

    private const val url = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    lateinit var service: PicPayService

    fun init(){
        val okHttp = OkHttpClient.Builder().build()

        if (!::service.isInitialized){
            service = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(PicPayService::class.java)
        }
    }
}