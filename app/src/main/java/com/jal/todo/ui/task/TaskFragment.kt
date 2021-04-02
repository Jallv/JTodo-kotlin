package com.jal.todo.ui.task

import android.text.format.DateUtils.isToday
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.jal.core.base.BaseVMFragment
import com.jal.core.ext.FormatType
import com.jal.core.ext.dateFormat
import com.jal.todo.BR
import com.jal.todo.R
import com.jal.todo.databinding.FragmentTaskBinding
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import org.koin.androidx.viewmodel.compat.ViewModelCompat.getViewModel

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
class TaskFragment : BaseVMFragment<FragmentTaskBinding, TaskViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_task

    override fun initViewModel() = getViewModel(this, TaskViewModel::class.java)

    override fun initVariableId() = BR.viewModel

    override fun initData() {
        binding.calendarView.setOnCalendarSelectListener(object :
            CalendarView.OnCalendarSelectListener {
            override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                if (!isClick) {
                    return
                }
                calendar?.let {
                    viewModel.currentTime.set(
                        if (isToday(it.timeInMillis)) {
                            resources.getString(R.string.app_today)
                        } else {
                            it.timeInMillis.dateFormat(
                                FormatType.yyyyMMdd
                            )
                        }
                    )
                }
            }

            override fun onCalendarOutOfRange(calendar: Calendar?) {

            }

        })
        binding.adapter = BindingRecyclerViewAdapter<TaskItemViewModel>()
    }

}