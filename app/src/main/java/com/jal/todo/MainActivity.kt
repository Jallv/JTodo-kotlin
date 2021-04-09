package com.jal.todo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jal.core.base.BaseActivity
import com.jal.todo.databinding.ActivityMainBinding
import com.jal.todo.ui.task.TaskFragment
import com.jal.todo.ui.time.TimerFragment
import java.util.*

class MainActivity : BaseActivity() {
    private val binding by binding<ActivityMainBinding>(R.layout.activity_main)
    private val fragments = mutableListOf<Fragment>()
    override fun initView() {

    }

    override fun initData() {
        fragments.apply {
            add(TaskFragment())
            add(TimerFragment())
            add(TimerFragment())
            add(TimerFragment())
        }
        binding.apply {
            viewPager.offscreenPageLimit = 4
            viewPager.adapter = object :
                FragmentStatePagerAdapter(
                    supportFragmentManager,
                    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
                ) {
                override fun getItem(position: Int): Fragment {
                    return fragments[position]
                }

                override fun getCount(): Int {
                    return fragments.size
                }
            }
            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                // ...
                override fun onPageSelected(position: Int) {
                    when (position) {
                        0 -> {
                            binding.bottomNavigationView.selectedItemId = R.id.navigation_task
                        }
                        1 -> {
                            binding.bottomNavigationView.selectedItemId = R.id.navigation_clock
                        }
                        2 -> {
                            binding.bottomNavigationView.selectedItemId = R.id.navigation_record
                        }
                        3 -> {
                            binding.bottomNavigationView.selectedItemId = R.id.navigation_my
                        }
                    }
                }
            })
            bottomNavigationView.setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.navigation_task -> {
                            binding.viewPager.currentItem = 0
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.navigation_clock -> {
                            binding.viewPager.currentItem = 1
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.navigation_record -> {
                            binding.viewPager.currentItem = 2
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.navigation_my -> {
                            binding.viewPager.currentItem = 3
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    false
                })
        }
    }

}