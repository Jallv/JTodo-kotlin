package com.jal.todo.ui.time

import android.app.Application
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.jal.core.base.BaseModel
import com.jal.core.base.BaseViewModel
import com.jal.core.binding.command.BindingAction
import com.jal.core.binding.command.BindingCommand
import com.jal.core.binding.command.VoidBindingCommand
import com.jal.todo.R


/**
 * @author aljiang
 * @date 2021/1/29
 * @desc
 */
class TimerViewModel constructor(application: Application) :
    BaseViewModel<BaseModel>(application) {
    var startTime = ObservableBoolean()

    var buttonText: ObservableField<String> =
        ObservableField(application.resources.getString(R.string.app_start_focus))
    var recordCommand: VoidBindingCommand =VoidBindingCommand(object : BindingAction {
        override fun call() {

        }
    })
    var startCommand: VoidBindingCommand = VoidBindingCommand(object : BindingAction {
        override fun call() {
            startTime.set(!startTime.get())
            buttonText.set(application.resources.getString(if (startTime.get()) R.string.app_give_up_focus else R.string.app_start_focus))
        }
    })
}