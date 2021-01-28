package com.jal.todo.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
@Entity
data class Task(
    @PrimaryKey
    val createTime: Long = 0,
    val date: String,
    val week: Int,
    val content: String,
    val isCompleted: Boolean = false,
    val remindTime: String? = null,
    val subTaskList: List<Task>? = null
)