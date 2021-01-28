package com.jal.todo.app

import android.content.Context

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
object ContextProvider {
    lateinit var mContext: Context

    fun init(context: Context) {
        this.mContext = context
    }
}