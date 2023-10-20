package com.example.company_oa_application.entity.fileStream
import com.example.company_oa_application.entity.hrmResource.Personal
import java.io.Serializable
import java.util.*

data class FileStream (
    val fileStreamId: Long = 0,
    val fileStreamSubject: String? = null,
    val fileContext: String? = null,
    val personals: List<Personal>? = null,
    val index:Int = 0,
    val appendixFiles: List<FileStreamAppendixFile>? = null,
    val personal: Personal? = null,
    val filePersonals: List<FilePersonals>? = null,
    val fileTime: Date? = null): Serializable {
}