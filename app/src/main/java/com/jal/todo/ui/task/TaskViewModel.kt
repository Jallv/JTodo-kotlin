package com.jal.todo.ui.task

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
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
class TaskViewModel constructor(application: Application) :
    BaseViewModel<BaseModel>(application) {
    val observableList: ObservableList<TaskItemViewModel> = ObservableArrayList<TaskItemViewModel>()
    val itemBinding: ItemBinding<TaskItemViewModel> =
        ItemBinding.of(BR.viewModel, R.layout.app_item_task)
    val currentTime = ObservableField<String>()

    init {
        currentTime.set(getApplication<Application>().getString(R.string.app_today))
        observableList.add(TaskItemViewModel(this))
    }
}