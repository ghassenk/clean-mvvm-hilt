package com.gk.app.android.testingviewmodels.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {

    companion object {
        // Used for global activity callbacks
        lateinit var appInstance: Application
    }

    override fun onCreate() {
        Log.i(javaClass.simpleName, "onCreate()")
        super.onCreate()

        appInstance = this
    }
}