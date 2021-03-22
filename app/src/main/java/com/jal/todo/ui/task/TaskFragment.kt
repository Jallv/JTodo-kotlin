package com.jal.todo.ui.task

import androidx.fragment.app.viewModels
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import com.iflytek.common.DateUtil
import com.iflytek.common.ResourcesUtil
import com.jal.core.base.BaseFragment
import com.jal.todo.R
import com.jal.todo.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import com.jal.todo.BR
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter
import java.util.*

/**
 * @author aljiang
 * @date 2021/1/28
 * @desc
 */
@AndroidEntryPoint
class TaskFragment : BaseFragment<FragmentTaskBinding, TaskViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_task

    override fun initViewModel() = viewModels<TaskViewModel>().value

    override fun initVariableId() = BR.viewModel

    override fun initData() {
        binding.apply {
            calendarView.setOnCalendarSelectListener(object :
                CalendarView.OnCalendarSelectListener {
                override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                    if (!isClick) {
                        return
                    }
                    calendar?.let {

                        viewModel.currentTime.set(
                            if (DateUtil.isToday(Date(it.timeInMillis)))
                                ResourcesUtil.getString(R.string.today)
                            else
                                DateUtil.formatLong(
                                    it.timeInMillis,
                                    DateUtil.FormatType.yyyyMMdd
                                )
                        )
                    }
                }

                override fun onCalendarOutOfRange(calendar: Calendar?) {

                }

            })
            adapter = BindingRecyclerViewAdapter<TaskItemViewModel>()
        }
    }

}