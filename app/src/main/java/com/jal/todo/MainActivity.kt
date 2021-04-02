package com.jal.todo

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jal.core.base.BaseActivity
import com.jal.todo.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)

    override fun initView() {
        binding.bottomNavigationView.setupWithNavController(
            (supportFragmentManager.findFragmentById(
                R.id.nav_host_fragment
            ) as NavHostFragment).navController
        )
    }

    override fun initData() {

    }
}