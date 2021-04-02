package com.jal.core.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

/**
 * @author aljiang
 * @date 2021/1/26
 * @desc
 */
abstract class BaseVMFragment<M : ViewDataBinding, T : BaseViewModel<BaseModel>> : Fragment() {
    lateinit var viewModel: T
    lateinit var binding: M
    private var viewModelId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, getLayoutResId(), null, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewDataBinding()
        initData()
        initViewObservable()
        registerUIChangeLiveDataCallBack()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }

    /*
     * 私有的初始化Databinding和ViewModel方法
     */
    private fun initViewDataBinding() {
        viewModel = initViewModel()
        viewModelId = initVariableId()
        binding.setVariable(viewModelId, viewModel)
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.lifecycleOwner = this
        lifecycle.addObserver(viewModel)
    }

    private fun registerUIChangeLiveDataCallBack() {
        viewModel.uc.startActivityEvent.observe(this, Observer {
            it?.let {
                val clz = it[BaseViewModel.CLASS] as Class<*>
                val bundle = it[BaseViewModel.BUNDLE] as? Bundle
                val obj = it[BaseViewModel.REQUEST_CODE] as? Int
                if (obj != 0) {
                    startActivity(clz, bundle, obj)
                } else {
                    startActivity(clz, bundle)
                }
            }
        })
        viewModel.uc.finishEvent.observe(this, Observer {
            activity?.finish()
        })
    }

    private fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(context, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    private fun startActivity(
        clz: Class<*>,
        bundle: Bundle?,
        code: Int?
    ) {
        val intent = Intent(context, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        if (code != null) {
            startActivityForResult(intent, code)
        } else {
            startActivity(intent)
        }
    }

    /**
     * 页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
     */
    open fun initViewObservable() {}

    /**
     * onCreate时初始化参数
     */
    open fun initParam() {}

    /**
     * 初始化ViewModel的id
     */
    protected abstract fun initVariableId(): Int

    /**
     * 初始化根布局
     */
    protected abstract fun getLayoutResId(): Int

    protected abstract fun initViewModel(): T

    protected abstract fun initData()
}