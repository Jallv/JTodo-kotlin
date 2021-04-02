package com.jal.core.binding.viewadapter

import android.widget.CheckBox
import androidx.databinding.BindingAdapter
import com.jal.core.binding.command.BindingCommand

/**
 *
 * @author: aljiang
 * @date: 2021/3/30 15:28
 * @desc:
 */
@BindingAdapter(value = ["onCheckedChangedCommand"], requireAll = false)
fun CheckBox.setCheckedChanged(common: BindingCommand<Boolean>) {
    this.setOnCheckedChangeListener { _, isChecked ->
        run {
            common.execute(isChecked)
        }
    }
}