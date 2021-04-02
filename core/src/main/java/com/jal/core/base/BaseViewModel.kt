package com.jal.core.base

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.jal.core.binding.command.BindingAction
import com.jal.core.binding.command.VoidBindingCommand
import com.jal.core.event.SingleLiveEvent
import java.util.*
import kotlin.collections.MutableMap
import kotlin.collections.set

/**
 * @author aljiang
 * @date 2021/2/8
 * @desc
 */
open class BaseViewModel<M : BaseModel?> constructor(
    application: Application,
    private var model: M? = null
) : AndroidViewModel(application), IBaseViewModel {
    val uc by lazy {
        UIChangeLiveData()
    }

    var backCommand: VoidBindingCommand? = VoidBindingCommand(object : BindingAction {
        override fun call() {
            finish()
        }
    })

    override fun onCreate() {}
    override fun onResume() {}
    override fun onPause() {}
    override fun onStop() {}
    override fun onDestroy() {}
    override fun onCleared() {
        super.onCleared()
        model?.onCleared()
    }
    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    fun startActivity(clz: Class<*>, bundle: Bundle? = null) {
        val params: MutableMap<String, Any> = HashMap()
        params[CLASS] = clz
        if (bundle != null) {
            params[BUNDLE] = bundle
        }
        uc.startActivityEvent.postValue(params)
    }

    fun startActivity(
        clz: Class<*>,
        bundle: Bundle?,
        requestCode: Int
    ) {
        val params: MutableMap<String, Any> = HashMap()
        params[CLASS] = clz
        params[REQUEST_CODE] = requestCode
        if (bundle != null) {
            params[BUNDLE] = bundle
        }
        uc.startActivityEvent.postValue(params)
    }

    /**
     * 关闭界面
     */
    fun finish() {
        uc.finishEvent.call()
    }

    class UIChangeLiveData {
        val startActivityEvent by lazy { SingleLiveEvent<MutableMap<String, Any>>() }
        val finishEvent by lazy { SingleLiveEvent<Void>() }
    }

    open class UiState<T>(
        val isLoading: Boolean = false,
        val isRefresh: Boolean = false,
        val isSuccess: T? = null,
        val isError: String? = null
    )

    companion object {
        const val CLASS = "CLASS"
        const val BUNDLE = "BUNDLE"
        const val REQUEST_CODE = "REQUEST_CODE"
    }
}