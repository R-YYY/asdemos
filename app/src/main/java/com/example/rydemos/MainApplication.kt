package com.example.rydemos

import android.app.Application

class MainApplication : Application() {

    companion object {
        lateinit var mApplicationContext: MainApplication
    }

    override fun onCreate() {
        super.onCreate()
        mApplicationContext = this
    }
}