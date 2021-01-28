package com.jal.todo.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jal.todo.data.entity.Task

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
@Database(entities = [Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}