package com.example.company_oa_application.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.company_oa_application.R
import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.activity.MainActivity
import com.example.company_oa_application.adapter.AnnouncementItemAdapter
import com.example.company_oa_application.adapter.InnerMailItemAdapter
import com.example.company_oa_application.adapter.MissionItemAdapter
import com.example.company_oa_application.data.entity.newsAndAnnouncement.Announcement
import com.example.company_oa_application.databinding.FragmentInnerMailListBinding
import com.example.company_oa_application.databinding.FragmentInnerMailListItemBinding
import com.example.company_oa_application.entity.mail.innerMail.Mail
import com.example.company_oa_application.entity.mission.Mission
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.FormBody
import java.lang.reflect.Type
import kotlin.concurrent.thread

/**
 * A fragment representing a list of Items.
 */
class MissionItemFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var missions = ArrayList<Mission>()
        val connectWebUtil = ConnectWebUtil.getInstance()
        val view = inflater.inflate(R.layout.fragment_mission_list, container, false)
        var adapter = MissionItemAdapter(missions)
        if (view is RecyclerView) {
            view.adapter = adapter
        }
        thread {
            val host = getString(R.string.host) + "/mission/getMissionsByMissionUser/${MainActivity.loginPersonal?.user?.userId}"
            val build = FormBody.Builder().build()
            val messages = connectWebUtil.post(build, host)
            val type: Type = object : TypeToken<List<Mission?>?>() {}.type
            if (messages != "")
                missions = Gson().fromJson(messages, type)
            adapter.changeMissions(missions)
            ActivityList.getThisActivity().runOnUiThread {
                adapter.notifyDataSetChanged()
                if (view is RecyclerView) {
                    view.adapter = adapter
                }
            }
        }
        return view
    }
}