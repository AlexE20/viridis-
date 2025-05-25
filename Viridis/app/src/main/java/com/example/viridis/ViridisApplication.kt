package com.example.viridis

import android.app.Application
import com.example.viridis.data.AppProvider

class ViridisApplication : Application() {

    // Expose singleton
    lateinit var appProvider: AppProvider
        private set

    //Singleton
    override fun onCreate() {
        super.onCreate()

        appProvider = AppProvider.getInstance(this)
    }
}
