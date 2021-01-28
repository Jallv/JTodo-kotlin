package com.jal.todo.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jal.todo.data.entity.Task
import kotlinx.coroutines.flow.Flow


/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
@Dao
interface TaskDao {
    @Query("SELECT * from task")
    fun getAllTask(): Flow<List<Task>>

    @Query("SELECT * from task WHERE date=:date OR date='-1' OR (week & :week)!=0 ORDER BY createTime DESC")
    fun getTaskByDate(
        date: String?,
        week: Int
    ): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg tasks: Task?)

    @Delete
    fun delete(vararg tasks: Task?)
}