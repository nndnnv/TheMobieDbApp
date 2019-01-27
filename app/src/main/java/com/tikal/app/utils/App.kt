package com.tikal.app.utils

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {

    // use this Context for only Application context!
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
            private set
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}
