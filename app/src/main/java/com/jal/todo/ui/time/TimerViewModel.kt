package com.jal.todo.ui.time

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.haibin.calendarview.BaseView
import com.jal.core.base.BaseModel
import com.jal.core.base.BaseViewModel

/**
 * @author aljiang
 * @date 2021/1/29
 * @desc
 */
class TimerViewModel @ViewModelInject constructor(application: Application) :
    BaseViewModel<BaseModel>(application) {

}