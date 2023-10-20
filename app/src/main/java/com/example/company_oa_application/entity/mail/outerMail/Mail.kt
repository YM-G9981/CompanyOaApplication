package com.example.company_oa_application.entity.mail.outerMail
import com.example.company_oa_application.entity.hrmResource.User
import java.io.Serializable
import java.util.*

data class Mail (
    var mailId: Long = 0,
    var subject: String? = null,
    var text: String? = null,
    var sendTo: String? = null,
    var user: User? = null,
    var mailState:Int = 0,
    var appendixFiles: List<AppendixFile>? = null,
    var sendTime: Date? = null): Serializable