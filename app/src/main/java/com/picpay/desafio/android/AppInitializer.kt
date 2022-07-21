package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.service.ApiService

class AppInitializer: Application() {
    override fun onCreate() {
        super.onCreate()
        ApiService.init(this)
    }
}