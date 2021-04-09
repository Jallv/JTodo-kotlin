package com.jal.todo.data.repository

import com.jal.todo.data.dao.SubTaskDao
import com.jal.todo.data.dao.TaskDao
import com.jal.todo.data.entity.SubTask
import com.jal.todo.data.entity.Task

/**
 *
 * @author: aljiang
 * @date: 2021/4/9 16:06
 * @desc:
 */
class TaskManager(private val taskDao: TaskDao, private val subTaskDao: SubTaskDao) {
    fun insertTask(task: Task, subTaskList: List<SubTask>?) {
        taskDao.insert(task)
        subTaskList?.let {
            subTaskDao.insert(it)
        }
    }

    fun deleteTask(task: Task) {
        taskDao.delete(task)
        subTaskDao.delete(task.id)
    }

    fun queryTaskResult() {

    }

}