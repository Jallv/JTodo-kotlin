package com.jal.tibet.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel


/**
 * @author aljiang
 * @date 2021/1/21
 * @desc
 */
abstract class BaseActivity<M : ViewDataBinding, T : ViewModel> : AppCompatActivity() {
    lateinit var mViewModel: T
    lateinit var mViewBinding: M
    private var viewModelId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewBinding = DataBindingUtil.setContentView(this, getLayoutResId())
        mViewModel = initViewModel()
        viewModelId = initVariableId()
        mViewBinding.setVariable(viewModelId, mViewModel)
        initData()
    }

    abstract fun initVariableId(): Int
    
    protected abstract fun getLayoutResId(): Int

    protected abstract fun initViewModel(): T

    protected abstract fun initData()
}