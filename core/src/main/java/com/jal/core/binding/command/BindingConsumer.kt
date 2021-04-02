package com.jal.core.binding.command

/**
 *
 * @author: aljiang
 * @date: 2021/3/30 15:17
 * @desc:
 */
interface BindingConsumer<T> {
    fun call(t: T?)
}