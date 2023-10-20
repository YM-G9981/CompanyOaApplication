package com.example.company_oa_application.activity

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.company_oa_application.adapter.AnnouncementFileItemAdapter
import com.example.company_oa_application.data.entity.newsAndAnnouncement.Announcement
import com.example.company_oa_application.databinding.ActivityShowAnnouncementBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.text.SimpleDateFormat

class ShowAnnouncementActivity : BaseActivity() {

    private lateinit var binding: ActivityShowAnnouncementBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowAnnouncementBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val announcementString = intent.getStringExtra("showAnnouncement")
        val type: Type = object : TypeToken<Announcement?>() {}.type
        val announcement: Announcement = Gson().fromJson(announcementString, type)
        binding.announcementContext.text = announcement.announcementContext
        binding.announcementTime.text = SimpleDateFormat("yyyy-MM-dd").format(announcement.announcementDate)
        binding.showAnnouncementTitle.text = announcement.announcementTitle
        binding.showAnnouncementTitleBar.setLeftClickListener {
            super.onDestroy()
        }
        var appendixFiles = announcement.announcementAppendixFiles!!
        var adapter = AnnouncementFileItemAdapter(appendixFiles)
        binding.newsAppendixFileList.adapter = adapter
    }
}