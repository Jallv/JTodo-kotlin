package com.jal.todo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.jal.todo.data.entity.SubTask
import kotlinx.coroutines.flow.Flow

/**
 *
 * @author: aljiang
 * @date: 2021/4/9 16:00
 * @desc:
 */
@Dao
interface SubTaskDao {
    @Query("SELECT * from subTask")
    fun getAllTask(): Flow<List<SubTask>>

    @Insert
    fun insert(tasks: List<SubTask>)

    @Delete
    fun delete(tasks: List<SubTask>)

    @Query("DELETE from subTask WHERE taskId=:taskId")
    fun delete(taskId: Int)
}