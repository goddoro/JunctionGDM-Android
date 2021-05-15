package com.goddoro.junction.application

import android.app.Application
import com.goddoro.junction.di.apiModule
import com.goddoro.junction.di.networkModule
import com.goddoro.junction.di.repositoryModule
import com.goddoro.junction.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


/**
 * created By DORO 5/15/21
 */

class MainApplication : Application() {


    override fun onCreate() {
        super.onCreate()

        inject()
    }



    private fun inject() {

        startKoin {
            androidContext(this@MainApplication)
            androidLogger(Level.INFO)
            modules(
                listOf(
                    apiModule,
                    repositoryModule,
                    networkModule,
                    viewModelModule
                )

            )

        }
    }
}