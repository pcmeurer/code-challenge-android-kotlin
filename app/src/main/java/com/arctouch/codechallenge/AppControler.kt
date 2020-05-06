package com.arctouch.codechallenge

import android.app.Application
import com.arctouch.codechallenge.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppControler : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppControler)
            modules(appModules)
        }
    }
}