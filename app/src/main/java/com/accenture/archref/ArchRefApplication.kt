package com.accenture.archref

import android.app.Application
import com.accenture.archref.di.addModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ArchRefApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@ArchRefApplication)
            modules(addModules)
        }
    }
}
