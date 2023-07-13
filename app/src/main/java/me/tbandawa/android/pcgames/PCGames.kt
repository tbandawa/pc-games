package me.tbandawa.android.pcgames

import android.app.Application
import me.tbandawa.android.pcgames.di.initKoin
import org.koin.android.ext.koin.androidContext
import timber.log.Timber

class PCGames: Application() {

    override fun onCreate() {
        super.onCreate()

        // start koin
        initKoin {
            androidContext(this@PCGames)
        }

        // enable Timber
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

    }
}