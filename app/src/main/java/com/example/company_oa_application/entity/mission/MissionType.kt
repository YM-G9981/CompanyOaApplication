package com.example.company_oa_application.entity.mission

import java.io.Serializable

data class MissionType(val missions: List<Mission>? = null, val missionTypeId: Long = 0, val missionTypeTitle: String? = null, val missionTypeContext: String? = null):Serializable