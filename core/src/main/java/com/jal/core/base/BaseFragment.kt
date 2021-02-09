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
abstract class BaseFragment<M : ViewDataBinding, T : BaseViewModel<BaseModel>> : Fragment() {
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

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }

    private fun registerUIChangeLiveDataCallBack() {
        viewModel.uc.showDialogEvent.observe(this, Observer {
            showDialog(it)
        })
        viewModel.uc.dismissDialogEvent.observe(this, Observer {
            dismissDialog()
        })
        viewModel.uc.startActivityEvent.observe(this, Observer {
            val clz = it[BaseViewModel.ParameterField.CLASS] as Class<*>
            val bundle = it[BaseViewModel.ParameterField.BUNDLE] as Bundle
            val obj: Int = it[BaseViewModel.ParameterField.REQUEST_CODE] as Int
            if (obj != 0) {
                startActivity(clz, bundle, obj)
            } else {
                startActivity(clz, bundle)
            }
        })
        viewModel.uc.finishEvent.observe(this, Observer {
            activity?.finish()
        })
    }

    open fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(context, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }

    open fun startActivity(
        clz: Class<*>,
        bundle: Bundle?,
        code: Int
    ) {
        val intent = Intent(context, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivityForResult(intent, code)
    }

    fun showDialog(title: String) {}
    fun dismissDialog() {}

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