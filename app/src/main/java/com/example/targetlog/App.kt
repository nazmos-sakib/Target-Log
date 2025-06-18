package com.example.targetlog

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

@HiltAndroidApp
class App:Application() {
    val appCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    override fun onCreate() {
        super.onCreate()

    }

    override fun onTerminate() {
        appCoroutineScope.cancel()
        super.onTerminate()
    }
}