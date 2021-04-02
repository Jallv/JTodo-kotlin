package com.jal.core.binding.viewadapter

import android.graphics.drawable.Drawable
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

/**
 *
 * @author: aljiang
 * @date: 2021/3/30 15:34
 * @desc:
 */
@BindingAdapter(value = ["url", "placeholderRes"], requireAll = false)
fun ImageView.setImageUri(
    url: String,
    placeholderRes: Drawable
) {
    load(url) {
        placeholder(placeholderRes)
    }
}