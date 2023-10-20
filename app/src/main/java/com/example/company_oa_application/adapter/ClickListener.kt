package com.example.company_oa_application.adapter

import android.content.Intent
import android.view.View
import com.example.company_oa_application.activity.ActivityList

class ClickListener(val target: Class<*>?): View.OnClickListener{
    override fun onClick(p0: View?) {
        val intent = Intent(ActivityList.getThisActivity(), target)
        ActivityList.getThisActivity().startActivity(intent)
    }

}