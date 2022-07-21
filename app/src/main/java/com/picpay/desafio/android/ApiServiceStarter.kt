package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.service.ApiService

class ApiServiceStarter: Application() {
    override fun onCreate() {
        super.onCreate()
        ApiService.init()
    }
}