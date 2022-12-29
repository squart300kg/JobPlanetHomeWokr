package com.jobplanet.kr.android

import android.app.Application
import com.jobplanet.kr.android.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class Application: Application() {

    override fun onCreate() {
        super.onCreate()

        koinInitialize()

    }

    private fun koinInitialize() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@Application)
            modules(listOf(
                preferencesModule,
                networkModule,
                repositoryModule,
                viewModelModule))
        }

    }
}

