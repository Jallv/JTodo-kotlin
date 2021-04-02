package com.jal.core.binding.viewadapter

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.jal.core.binding.command.BindingCommand

/**
 *
 * @author: aljiang
 * @date: 2021/3/30 15:32
 * @desc: EditText输入文字改变的监听
 */
@BindingAdapter(value = ["textChanged"], requireAll = false)
fun EditText.addTextChangedListener(
    textChanged: BindingCommand<String>
) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(
            charSequence: CharSequence,
            i: Int,
            i1: Int,
            i2: Int
        ) {
        }

        override fun onTextChanged(text: CharSequence, i: Int, i1: Int, i2: Int) {
            textChanged.execute(text.toString())
        }

        override fun afterTextChanged(editable: Editable) {}
    })
}