package com.example.company_oa_application.activity;

import android.app.Activity;
import android.os.Bundle
import com.example.company_oa_application.R
import com.xuexiang.xui.widget.actionbar.TitleBar

class MissionActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_mission)
        val newsActivityTitle = findViewById<TitleBar>(R.id.mission_activity_title)
        newsActivityTitle.setLeftClickListener{
            super.onDestroy()
        }
    }
}