package com.jal.todo.app

import android.app.Application
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
    override fun onCreate() {
        super.onCreate()
        startKoin {
            AndroidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule)
        }
    }
}