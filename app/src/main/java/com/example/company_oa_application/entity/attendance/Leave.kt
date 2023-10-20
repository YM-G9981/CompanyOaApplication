package com.example.company_oa_application.entity.attendance

import com.example.company_oa_application.entity.hrmResource.Personal
import java.io.Serializable
import java.util.*

data class Leave(val leaveId: Long,val leaveBeginDate: Date?,val leaveEndDate: Date?,val leavePersonal: Personal?) : Serializable