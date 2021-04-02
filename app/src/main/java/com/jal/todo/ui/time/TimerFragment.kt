package com.jal.todo.ui.time

import com.jal.core.base.BaseVMFragment
import com.jal.todo.BR
import com.jal.todo.R
import com.jal.todo.databinding.FragmentTimerBinding
import org.koin.androidx.viewmodel.compat.ViewModelCompat.getViewModel

/**
 * @author aljiang
 * @date 2021/1/29
 * @desc
 */
class TimerFragment : BaseVMFragment<FragmentTimerBinding, TimerViewModel>() {
    override fun initVariableId() = BR.viewModel

    override fun getLayoutResId() = R.layout.fragment_timer

    override fun initViewModel() = getViewModel(this, TimerViewModel::class.java)

    override fun initData() {

    }
}