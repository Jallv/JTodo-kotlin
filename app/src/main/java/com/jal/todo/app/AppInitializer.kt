package com.jal.todo.app

import android.content.Context
import androidx.startup.Initializer
import com.iflytek.common.ContextProvider

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        ContextProvider.init(context)
        return Unit
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}