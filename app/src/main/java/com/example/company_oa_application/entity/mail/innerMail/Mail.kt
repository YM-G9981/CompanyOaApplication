package com.example.company_oa_application.entity.mail.innerMail

import com.example.company_oa_application.entity.hrmResource.User
import java.io.Serializable
import java.util.*

data class Mail(
    var mailId: Long = 0,
    var mailTitle: String? = null,
    var mailContext: String? = null,
    var mailSummary: String? = null,
    var appendixFiles: List<AppendixFile>? = null,
    var sendTime: Date? = null,
    var addresser: User? = null,
    var addressee: User? = null,
    var replyFrom: Mail? = null,
    var mailState:Int= 0): Serializable