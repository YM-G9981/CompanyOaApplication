package com.example.company_oa_application.data.entity.newsAndAnnouncement


import java.io.Serializable
import java.util.*

data class News (
     val newsId: Long = 0
    , val newsTitle: String? = null
    , val newsDescribe: String? = null
    , val newsInformation: String? = null
    , val createTime: Date? = null
    , val newsAppendixFiles: List<NewsAppendixFile>? = null
    , val newsState:Int = 0): Serializable