package com.example.company_oa_application.entity.hrmResource

import java.io.Serializable
import java.util.*

data class Department(var departmentId: Long = 0, var departmentName: String="", var departmentDescribe: String="", var departmentState:Int = 0, var departmentManager: Personal?=null, var departmentLocation: String?=null, var superiorDepartment: Department?=null, var departmentCreateTime: Date?=null, var personals: List<Personal>?=null) : Serializable