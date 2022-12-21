package com.floward.assignment.di

import android.content.Context
import com.floward.assignment.prefences.PrefManager
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * DIFramework.kt
 * The dependency injection framework used by the app.
 * uses Koin for DI.
 */
object DIFramework {

    fun init(context: Context) {
        val repoModule = module {
            //  Pref Manger
            single { PrefManager.getInstance() }
        }

        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(context)
            // declare modules
            modules(repoModule)
        }
    }
}