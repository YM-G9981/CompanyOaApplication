package com.example.company_oa_application.adapter

import androidx.viewpager2.widget.ViewPager2
import com.jpeng.jptabbar.OnTabSelectListener

class OnTabSelectedListenerImpl(val viewPager: ViewPager2): OnTabSelectListener {
    override fun onTabSelect(index: Int) {
        viewPager.currentItem = index
    }

    override fun onInterruptSelect(index: Int): Boolean {
        return false
    }
}