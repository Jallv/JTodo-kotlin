package com.jal.todo.binding.viewadapter

import androidx.databinding.BindingAdapter
import com.jal.todo.widget.CountDownView

@BindingAdapter("start")
fun start(countDownView: CountDownView, start: Boolean) {
    if (start) {
        countDownView.start()
    } else {
        countDownView.reset()
    }
}