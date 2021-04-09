package com.jal.todo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *
 * @author: aljiang
 * @date: 2021/4/9 15:39
 * @desc:
 */
@Entity(tableName = "subTask")
data class SubTask(
    @PrimaryKey(autoGenerate = true)
    val subTaskId: Int = 0,
    val taskId: Int = 0,
    val createTime: Long = 0,
    val content: String,
    val isCompleted: Boolean = false,
    val repeatId: Long,
    val subRepeatId: Long
)