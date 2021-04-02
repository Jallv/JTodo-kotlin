package com.jal.todo.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.jal.todo.BuildConfig
import com.jal.todo.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
class App : Application() {
    companion object {
        lateinit var mInstance: App
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
        startKoin {
            if (BuildConfig.DEBUG) {
                AndroidLogger(Level.DEBUG)
            }
            androidContext(this@App)
            modules(appModule)
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this);
    }
}