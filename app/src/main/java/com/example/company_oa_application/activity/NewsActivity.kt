package com.example.company_oa_application.activity

import android.os.Bundle
import com.example.company_oa_application.R
import com.xuexiang.xui.widget.actionbar.TitleBar

class NewsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_news)
        val newsActivityTitle = findViewById<TitleBar>(R.id.news_activity_title)
        newsActivityTitle.setLeftClickListener{
            super.onDestroy()
        }
    }
}