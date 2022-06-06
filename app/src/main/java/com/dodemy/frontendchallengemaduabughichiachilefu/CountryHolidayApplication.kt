package com.dodemy.frontendchallengemaduabughichiachilefu

import android.app.Application
//import androidx.viewbinding.BuildConfig
import com.dodemy.frontendchallengemaduabughichiachilefu.di.appModule
import com.dodemy.frontendchallengemaduabughichiachilefu.di.holidayModule
import com.dodemy.frontendchallengemaduabughichiachilefu.di.homeModule
import com.dodemy.frontendchallengemaduabughichiachilefu.di.loginModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class CountryHolidayApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(androidContext = this@CountryHolidayApplication)
            modules(
                loginModule,
                appModule,
                homeModule,
                holidayModule
            )
        }
    }
}
