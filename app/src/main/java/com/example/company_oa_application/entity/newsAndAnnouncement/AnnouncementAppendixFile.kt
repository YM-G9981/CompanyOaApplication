package com.example.company_oa_application.data.entity.newsAndAnnouncement

import java.io.*

data class AnnouncementAppendixFile(
      val fileId: Long = 0
    , val file: String? = null
    , val name: String? = null
    , val announcement: Announcement? = null) : Serializable {
}