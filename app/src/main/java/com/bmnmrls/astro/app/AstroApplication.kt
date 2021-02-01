package com.bmnmrls.astro.app

import android.app.Application
import com.bmnmrls.astro.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class AstroApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setupLogger()
    }

    private fun setupLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
