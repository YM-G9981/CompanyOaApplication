package com.example.company_oa_application.data.entity.newsAndAnnouncement

import java.io.*
data class NewsAppendixFile (
    val fileId: Long = 0
    ,val file: String? = null
    ,val name: String? = null
    ,val news: News? = null): Serializable {
}