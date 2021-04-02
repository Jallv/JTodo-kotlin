package com.jal.todo.ui.task

import android.text.format.DateUtils.isToday
import androidx.fragment.app.viewModels
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.jal.core.base.BaseFragment
import com.jal.core.common.FormatType
import com.jal.core.common.formatLong
import com.jal.todo.BR
import com.jal.todo.R
import com.jal.todo.databinding.FragmentTaskBinding
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
class TaskFragment : BaseFragment<FragmentTaskBinding, TaskViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_task

    override fun initViewModel() = viewModels<TaskViewModel>().value

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
                            resources.getString(R.string.today)
                        } else {
                            formatLong(
                                it.timeInMillis,
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