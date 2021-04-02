package com.jal.core

import android.content.Context

/**
 *
 * @author: aljiang
 * @date: 2021/3/30 15:50
 * @desc:
 */
private lateinit var context: Context

fun providerContext(c: Context) {
    context = c
}

fun getContext(): Context {
    return context
}