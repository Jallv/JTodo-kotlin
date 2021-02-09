package com.jal.todo.ui.time

import androidx.fragment.app.viewModels
import com.jal.core.base.BaseFragment
import com.jal.todo.BR
import com.jal.todo.R
import com.jal.todo.databinding.FragmentTimerBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * @author aljiang
 * @date 2021/1/29
 * @desc
 */
@AndroidEntryPoint
class TimerFragment : BaseFragment<FragmentTimerBinding, TimerViewModel>() {
    override fun initVariableId() = BR.viewModel

    override fun getLayoutResId() = R.layout.fragment_timer

    override fun initViewModel() = viewModels<TimerViewModel>().value

    override fun initData() {

    }
}