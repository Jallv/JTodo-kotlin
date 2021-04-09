package com.jal.todo.data.dao

import androidx.room.*
import com.jal.todo.data.entity.Task
import com.jal.todo.data.entity.TaskResult
import kotlinx.coroutines.flow.Flow


/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    fun getAllTask(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE date=:date ORDER BY createTime DESC")
    fun getTaskByDate(
        date: String,
    ): Flow<List<TaskResult>>

    @Insert
    fun insert(vararg tasks: Task)

    @Delete
    fun delete(vararg tasks: Task)
}