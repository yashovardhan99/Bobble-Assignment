package com.yashovardhan99.bobblebigtext

import android.app.Application
import timber.log.Timber

class BigTextApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}