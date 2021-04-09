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
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val createTime: Long,
    val date: String,
    val content: String,
    val isCompleted: Boolean,
    val remindTime: String? = null,
    val repeatId: Long,
    val repeatType: Int
)