package com.example.viridis

import android.app.Application
import com.example.viridis.data.AppProvider

class ViridisApplication : Application() {

    val appProvider by lazy {
        AppProvider(this)
    }
}
