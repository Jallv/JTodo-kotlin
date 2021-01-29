package com.jal.tibet.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

/**
 * @author aljiang
 * @date 2021/1/26
 * @desc
 */
abstract class BaseFragment<M : ViewDataBinding, T : ViewModel> : Fragment() {
    lateinit var mViewModel: T
    lateinit var mViewBinding: M
    private var viewModelId: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = DataBindingUtil.inflate(layoutInflater, getLayoutResId(), null, false)
        mViewModel = initViewModel()
        viewModelId = initVariableId()
        mViewBinding.setVariable(viewModelId, mViewModel)
        initData()
        return mViewBinding.root
    }

    protected abstract fun initVariableId(): Int

    protected abstract fun getLayoutResId(): Int

    protected abstract fun initViewModel(): T

    protected abstract fun initData()
}