package com.example.company_oa_application.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.company_oa_application.R
import com.example.company_oa_application.databinding.ActivityMainBinding
import com.example.company_oa_application.adapter.FragmentAdapter
import com.example.company_oa_application.adapter.OnTabSelectedListenerImpl
import com.example.company_oa_application.entity.hrmResource.Personal
import com.example.company_oa_application.fragment.FragmentMessageSetting
import com.example.company_oa_application.fragment.AddressListFragment
import com.example.company_oa_application.fragment.meau.MeauFragment
import com.jpeng.jptabbar.JPTabBar
import com.xuexiang.xui.XUI

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var tabBar = binding.tabBar
        val paper = binding.viewPager
        initViewPaper(paper)
        initTabBar(tabBar)
        var listener = OnTabSelectedListenerImpl(paper)
        tabBar.setTabListener(listener)
    }
    companion object{
        var loginPersonal:Personal?= null
    }
    private fun initViewPaper(paper:ViewPager2) {

        val fragmentList = ArrayList<Fragment>()
        fragmentList.apply {
            add(AddressListFragment.newInstance(2))
            add(MeauFragment.newInstance(3))
            add(FragmentMessageSetting())
        }
        var adapter = FragmentAdapter(this, fragmentList)
        paper.adapter = adapter
    }
    private fun initTabBar(tabBar:JPTabBar){
        tabBar.apply {
            setNormalIcons(
                R.drawable.nav_02_nor,
                R.drawable.nav_04_nor,
                R.drawable.nav_05_nor
            )
            setSelectedIcons(
                R.drawable.nav_02_pre,
                R.drawable.nav_04_pre,
                R.drawable.nav_05_pre
            )
            setTitles(
                R.string.tab2, R.string.tab3, R.string.tab4
            )
            generate()
            //页面可以滑动
            //页面可以滑动
            setGradientEnable(true)
            setPageAnimateEnable(true)
            setTabTypeFace(XUI.getDefaultTypeface())
        }
    }

}
