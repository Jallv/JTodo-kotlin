package com.jal.todo.di

import android.app.Application
import androidx.room.Room
import com.jal.todo.data.db.AppDatabase
import com.jal.todo.data.db.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
@Module
@InstallIn(ApplicationComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideAppDataBase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "todo.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providerTaskDao(appDataBase: AppDatabase): TaskDao {
        return appDataBase.taskDao()
    }
}