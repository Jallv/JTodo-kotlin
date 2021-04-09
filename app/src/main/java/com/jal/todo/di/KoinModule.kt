package com.jal.todo.di

import androidx.room.Room
import com.jal.core.getContext
import com.jal.todo.app.App
import com.jal.todo.data.AppDatabase
import com.jal.todo.data.repository.TaskManager
import com.jal.todo.ui.task.TaskViewModel
import com.jal.todo.ui.time.TimerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *
 * @author: aljiang
 * @date: 2021/3/30 15:11
 * @desc:
 */
val viewModelModule = module {
    viewModel { TaskViewModel(get()) }
    viewModel { TimerViewModel(get()) }
    factory {
        App.mInstance
    }
}

val repositoryModule = module {
    single {
        Room
            .databaseBuilder(getContext(), AppDatabase::class.java, "todo.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
    single {
        get<AppDatabase>().taskDao()
    }
    single {
        get<AppDatabase>().subTaskDao()
    }
    single {
        TaskManager(get(), get())
    }
}

val appModule = listOf(viewModelModule, repositoryModule)