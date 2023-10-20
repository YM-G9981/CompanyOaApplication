package com.example.company_oa_application.data.entity.newsAndAnnouncement

import com.example.company_oa_application.entity.hrmResource.Department
import java.util.*
import java.io.Serializable

data class Announcement(
      val announcementId: Long = 0
    , val announcementTitle: String? = null
    , val announcementContext: String? = null
    , val announcementDepartment: Department? = null
    , val announcementDate: Date? = null
    , val announcementAppendixFiles: List<AnnouncementAppendixFile>? = null
    , val announcementState:Int = 0 ): Serializable