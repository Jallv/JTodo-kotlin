package com.jal.todo.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {
    private var isCanScroll = false;

    /**
     * 设置其是否能滑动换页
     * @param isCanScroll false 不能换页， true 可以滑动换页
     */
    fun setScanScroll(isCanScroll: Boolean) {
        this.isCanScroll = isCanScroll;
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return isCanScroll && super.onInterceptTouchEvent(ev);
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        return isCanScroll && super.onTouchEvent(ev);

    }

    override fun setCurrentItem(item: Int, smoothScroll: Boolean) {
        super.setCurrentItem(item, smoothScroll);
    }

    override fun setCurrentItem(item: Int) {
        //去除页面切换时的滑动翻页效果
        super.setCurrentItem(item, false);
    }
}