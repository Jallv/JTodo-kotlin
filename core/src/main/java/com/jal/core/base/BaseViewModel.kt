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

    var backCommand: VoidBindingCommand? = VoidBindingCommand(BindingAction { finish() })

    fun showDialog(title: String? = "请稍后...") {
        uc.showDialogEvent.postValue(title)
    }

    fun dismissDialog() {
        uc.dismissDialogEvent.call()
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
        params[ParameterField.CLASS] = clz
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        uc.startActivityEvent.postValue(params)
    }

    fun startActivity(
        clz: Class<*>,
        bundle: Bundle?,
        requestCode: Int
    ) {
        val params: MutableMap<String, Any> = HashMap()
        params[ParameterField.CLASS] = clz
        params[ParameterField.REQUEST_CODE] = requestCode
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        uc.startActivityEvent.postValue(params)
    }

    /**
     * 关闭界面
     */
    fun finish() {
        uc.finishEvent.call()
    }

    override fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {}
    override fun onCreate() {}
    override fun onDestroy() {}
    override fun onStart() {}
    override fun onStop() {}
    override fun onResume() {}
    override fun onPause() {}
    override fun onCleared() {
        super.onCleared()
        model?.onCleared()
    }

    inner class UIChangeLiveData {
        val showDialogEvent by lazy { SingleLiveEvent<String>() }
        val dismissDialogEvent by lazy { SingleLiveEvent<Void>() }
        val startActivityEvent by lazy { SingleLiveEvent<MutableMap<String, Any>>() }
        val finishEvent by lazy { SingleLiveEvent<Void>() }
    }

    object ParameterField {
        const val CLASS = "CLASS"
        const val CANONICAL_NAME = "CANONICAL_NAME"
        const val BUNDLE = "BUNDLE"
        const val REQUEST_CODE = "REQUEST_CODE"
    }
}