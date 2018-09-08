package com.inc.silence.weather

import android.app.Application
import com.inc.silence.weather.di.appModule
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.startKoin

class App : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin(this, appModule)

        this.initializeLeakDetection()
    }

    private fun initializeLeakDetection() {
        if (BuildConfig.DEBUG) LeakCanary.install(this)
    }
}