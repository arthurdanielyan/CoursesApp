package com.example.coursesapp

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class CoursesApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Main + SupervisorJob())

    override fun onCreate() {
        super.onCreate()

        val applicationScopeModule = module {
            single { applicationScope }
        }

        startKoin {
            androidContext(applicationContext)
            modules(appModule + applicationScopeModule)
        }
    }
}