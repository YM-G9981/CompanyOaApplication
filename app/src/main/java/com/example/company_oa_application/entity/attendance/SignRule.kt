package com.example.company_oa_application.entity.attendance

import com.example.company_oa_application.entity.hrmResource.Personal
import java.io.Serializable
import java.sql.Time
 data class SignRule(val signRuleId: Double = 0.0,val signLatitude:Long = 0,val signLongitude:Double = 0.0,val signOffTime: Time?=null,val signOnTime: Time?=null,val signRadius:Double = 0.0,val signPersonals: List<Personal>?= null
) : Serializable