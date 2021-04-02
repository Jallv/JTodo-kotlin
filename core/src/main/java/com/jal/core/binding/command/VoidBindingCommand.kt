package com.jal.core.binding.command

/**
 *
 * @author: aljiang
 * @date: 2021/3/30 15:25
 * @desc:
 */
class VoidBindingCommand(execute: BindingAction) : BindingCommand<Unit>(execute)