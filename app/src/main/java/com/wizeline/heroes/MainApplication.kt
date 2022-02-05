package com.wizeline.heroes

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
//Primer clase que se ejecuta cuando ejecutas la app, y se mantiene viva
@HiltAndroidApp
class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}