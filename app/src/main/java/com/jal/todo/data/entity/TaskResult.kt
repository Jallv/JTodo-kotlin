package com.jal.todo.data.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 *
 * @author: aljiang
 * @date: 2021/4/9 15:57
 * @desc:
 */
data class TaskResult(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = "taskId"
    ) val subTaskList: List<SubTask>
)