package com.example.company_oa_application.entity.hrmResource

import java.io.Serializable
import java.util.*

data class User(val userId: Long = 0 ,val username: String?=null ,val password: String?=null ,val email: String?=null ,val personal: Personal?=null ,val createTime: Date?=null): Serializable{
    override fun toString(): String {
        return if(personal==null)"test" else personal!!.name.toString()
    }
}