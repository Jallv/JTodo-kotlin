package com.jal.core.binding.viewadapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.jal.core.binding.command.BindingCommand

/**
 *
 * @author: aljiang
 * @date: 2021/3/30 15:40
 * @desc:
 */
/**
 * requireAll 是意思是是否需要绑定全部参数, false为否
 * View的onClick事件绑定
 * onClickCommand 绑定的命令,
 * isThrottleFirst 是否开启防止过快点击
 */
@BindingAdapter(value = ["onClickCommand"])
fun View.onClickCommand(
    clickCommand: BindingCommand<Unit>,
) {
    setOnClickListener { clickCommand.execute() }
}

/**
 * view的onLongClick事件绑定
 */
@BindingAdapter(value = ["onLongClickCommand"], requireAll = false)
fun View.onLongClickCommand(clickCommand: BindingCommand<Unit>) {
    setOnLongClickListener {
        clickCommand.execute()
        false
    }
}


/**
 * view的显示隐藏
 */
@BindingAdapter(value = ["isVisible"], requireAll = false)
fun View.isVisible(visibility: Boolean?) {
    if (visibility == null || visibility) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}