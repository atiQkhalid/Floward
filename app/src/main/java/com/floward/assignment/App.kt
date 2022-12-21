package com.floward.assignment

import android.app.Application
import android.content.Context
import com.floward.assignment.di.DIFramework

/**
 * The App.kt, Application class
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        DIFramework.init(this)
    }

    companion object {
        lateinit var instance: App
        fun getAppContext(): Context {
            return instance
        }
    }
}