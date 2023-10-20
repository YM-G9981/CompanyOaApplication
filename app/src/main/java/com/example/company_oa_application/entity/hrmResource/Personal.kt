package com.example.company_oa_application.entity.hrmResource

import com.example.company_oa_application.entity.attendance.SignRule
import java.io.Serializable
import java.util.*


data class Personal(
    var personalId: Long = 0,
    var jobNumber: String?=null,
    var name: String?=null,
    var sex :Int = 0,
    var age : Int = 0,
    var birthday: Date?=null,
    var phone: String?=null,
    var hireDate: Date?=null,
    var idnumber: String?=null,
    var state :Int = 0,
    var superior: Personal?=null,
    var department: Department?=null,
    var position: Position?=null,
    var juniors: List<Personal>?=null,
    var signRule: SignRule?=null,
    var user: User?=null):Serializable{
    override fun toString(): String {
        return name!!
    }
}