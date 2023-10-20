package com.example.company_oa_application.entity.attendance

import com.example.company_oa_application.entity.hrmResource.User
import java.io.File
import java.io.Serializable
import java.util.*
data class Sign (
    var signId: Long = 0,
    var latitude:Double = 0.0,
    var longitude:Double = 0.0,
    var signLocation: String? = null,
    var signPicture: File?=null,
    var signStatus: Long = 0,
    var signTime: Date?=null,
    var signRule: SignRule?=null,
    var signUser: User?=null): Serializable