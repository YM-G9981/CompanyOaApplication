package com.example.company_oa_application.entity.mail.innerMail

import java.io.File
import java.io.Serializable

data class AppendixFile (
    var fileId: Long = 0 ,
    var file: String? = null ,
    var mail: Mail? = null): Serializable