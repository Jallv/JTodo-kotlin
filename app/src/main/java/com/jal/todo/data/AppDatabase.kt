package com.jal.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jal.todo.data.dao.SubTaskDao
import com.jal.todo.data.dao.TaskDao
import com.jal.todo.data.entity.SubTask
import com.jal.todo.data.entity.Task

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
@Database(entities = [Task::class, SubTask::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun subTaskDao(): SubTaskDao
}