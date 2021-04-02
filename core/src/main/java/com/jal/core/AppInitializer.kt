package com.jal.core

import android.content.Context
import androidx.startup.Initializer
import com.tencent.mmkv.MMKV

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
class AppInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        providerContext(context)
        MMKV.initialize(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}