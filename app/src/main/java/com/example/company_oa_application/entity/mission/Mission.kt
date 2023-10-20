package com.example.company_oa_application.entity.mission

import com.example.company_oa_application.entity.hrmResource.User
import java.io.Serializable
import java.util.*
data class Mission (
    val missionId: Long = 0,
    val missionTitle: String? = null,
    val missionType: MissionType? = null,
    val missionTime: Date? = null,
    val missionContext: String? = null,
    val deadlineTime: Date? = null,
    val missionInitiateUser: User? = null,
    val missionUser: List<User>? = null,
    var missionState :Boolean = false) : Serializable