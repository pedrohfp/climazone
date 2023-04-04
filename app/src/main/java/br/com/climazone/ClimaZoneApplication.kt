package br.com.climazone

import android.app.Application
import br.com.climazone.di.addModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class ClimaZoneApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@ClimaZoneApplication)
            modules(addModules)
        }
    }
}