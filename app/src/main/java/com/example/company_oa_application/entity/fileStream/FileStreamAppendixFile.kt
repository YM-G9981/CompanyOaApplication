package com.example.company_oa_application.entity.fileStream

import java.io.File
import java.io.Serializable

data class FileStreamAppendixFile (val fileId: Long = 0,val file: File? = null,val name: String? = null,val fileStream: FileStream? = null):Serializable