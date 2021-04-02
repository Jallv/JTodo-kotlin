package com.jal.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 *
 * @author: aljiang
 * @date: 2021/4/2 13:45
 * @desc:
 */
abstract class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initData()
    }

    abstract fun getLayoutResId(): Int

    abstract fun initView()

    abstract fun initData()
}