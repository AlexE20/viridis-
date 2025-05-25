package com.example.viridis

import android.app.Application
import com.example.viridis.data.AppProvider


class ViridisApp : Application() {
    val appProvider by lazy {
        AppProvider(this)
    }
}


