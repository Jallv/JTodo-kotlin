package com.jal.todo.task

import androidx.fragment.app.viewModels
import com.jal.tibet.base.BaseFragment
import com.jal.todo.R
import com.jal.todo.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint
import com.jal.todo.BR

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

    }

}