package com.jal.core.base

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import luyao.util.ktx.ext.permission.PermissionsCallbackDSL
import luyao.util.ktx.ext.permission.request


/**
 * @author aljiang
 * @date 2021/1/21
 * @desc
 */
abstract class BaseActivity<M : ViewDataBinding, T : BaseViewModel<BaseModel>> :
    AppCompatActivity() {
    lateinit var viewModel: T
    lateinit var binding: M
    private var viewModelId: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
        initViewDataBinding()
        initData()
        initViewObservable()
        registerUIChangeLiveDataCallBack()
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(viewModel)
    }

    private fun initViewDataBinding() {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
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
            finish()
        })
    }

    private fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val intent = Intent(this, clz)
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
        val intent = Intent(this, clz)
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
    abstract fun initVariableId(): Int

    protected abstract fun getLayoutResId(): Int

    protected abstract fun initViewModel(): T

    protected abstract fun initData()
}