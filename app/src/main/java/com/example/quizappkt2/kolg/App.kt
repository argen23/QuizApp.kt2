package com.example.quizappkt2.kolg

import android.app.Application
import com.example.quizappkt2.kolg.di.koinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
                modules(koinModules)
        }
    }
}