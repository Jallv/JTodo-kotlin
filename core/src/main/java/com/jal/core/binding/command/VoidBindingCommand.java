package com.jal.core.binding.command;

/**
 * @author aljiang
 * @date 2021/2/7
 * @desc
 */
public class VoidBindingCommand extends BindingCommand<Void> {
    public VoidBindingCommand(BindingAction execute) {
        super(execute);
    }
}
