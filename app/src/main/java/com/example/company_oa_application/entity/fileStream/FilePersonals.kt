package com.example.company_oa_application.entity.fileStream

import com.example.company_oa_application.entity.hrmResource.Personal
import java.io.Serializable
import java.util.*


data class FilePersonals(val filePersonalId: Long = 0, val fileStream: FileStream? = null, val personal: Personal? = null, val index:Int = 0, val pass: Boolean? = null, val passDate: Date? = null, val remarks: String? = null ): Serializable