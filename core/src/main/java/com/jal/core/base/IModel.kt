package com.jal.core.base

/**
 * @author aljiang
 * @date 2021/2/8
 * @desc
 */
interface IModel {
    /**
     * ViewModel销毁时清除Model，与ViewModel共消亡。Model层同样不能持有长生命周期对象
     */
    fun onCleared()
}