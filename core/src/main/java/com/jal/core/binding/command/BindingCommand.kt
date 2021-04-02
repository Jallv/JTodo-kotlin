package com.jal.core.binding.command

/**
 *
 * @author: aljiang
 * @date: 2021/3/30 15:18
 * @desc:
 */
open class BindingCommand<T> {
    private var execute: BindingAction? = null
    private var consumer: BindingConsumer<T>? = null

    constructor(execute: BindingAction) : super() {
        this.execute = execute
    }

    constructor(execute: BindingConsumer<T>) : super() {
        this.consumer = execute
    }


    /**
     * 执行BindingAction命令
     */
    fun execute() {
        execute?.call()
    }

    /**
     * 执行带泛型参数的命令
     *
     * @param parameter 泛型参数
     */
    fun execute(parameter: T) {
        consumer?.call(parameter)
    }
}