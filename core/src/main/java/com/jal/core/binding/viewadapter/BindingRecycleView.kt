package com.jal.core.binding.viewadapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jal.core.widget.SpaceItemDecoration

/**
 * Created by luyao
 * on 2020/1/17 11:03
 */

@BindingAdapter(
    "itemTopPadding",
    "itemLeftPadding",
    "itemBottomPadding",
    "itemRightPadding",
    requireAll = false
)
fun RecyclerView.addItemPadding(top: Float = 0F, left: Float = 0F, bottom: Float = 0F, right: Float = 0F) {
    addItemDecoration(SpaceItemDecoration(top.toInt(), left.toInt(), bottom.toInt(), right.toInt()))
}
