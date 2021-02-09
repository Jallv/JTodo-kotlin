package com.jal.todo.ui.task

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.iflytek.common.DateUtil
import com.iflytek.common.ResourcesUtil
import com.jal.core.base.BaseModel
import com.jal.core.base.BaseViewModel
import com.jal.todo.BR
import com.jal.todo.R
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
class TaskViewModel @ViewModelInject constructor(application: Application) :
    BaseViewModel<BaseModel>(application) {
    var observableList: ObservableList<TaskItemViewModel> = ObservableArrayList<TaskItemViewModel>()
    var itemBinding: ItemBinding<TaskItemViewModel> =
        ItemBinding.of(BR.viewModel, R.layout.item_task)
    val currentTime = ObservableField<String>()

    init {
        currentTime.set(ResourcesUtil.getString(R.string.today))
        observableList.add(TaskItemViewModel(this))
    }
}