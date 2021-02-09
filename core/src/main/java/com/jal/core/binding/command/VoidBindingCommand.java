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

    public VoidBindingCommand(BindingConsumer<Void> execute) {
        super(execute);
    }

    public VoidBindingCommand(BindingAction execute, BindingFunction<Boolean> canExecute0) {
        super(execute, canExecute0);
    }

    public VoidBindingCommand(BindingConsumer<Void> execute, BindingFunction<Boolean> canExecute0) {
        super(execute, canExecute0);
    }
}
