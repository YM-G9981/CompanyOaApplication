package com.example.company_oa_application.activity

import android.os.Bundle
import android.widget.Toast
import com.example.company_oa_application.R
import com.example.company_oa_application.databinding.ActivityShowMissionBinding
import com.example.company_oa_application.entity.mail.innerMail.Mail
import com.example.company_oa_application.entity.mission.Mission
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import okhttp3.FormBody
import java.text.SimpleDateFormat
import kotlin.concurrent.thread

class ShowMissionActivity : BaseActivity() {

    private lateinit var binding: ActivityShowMissionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowMissionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarLayout.title = title
        var showMissionString = intent.getSerializableExtra("showMission")
        var showMission:Mission = if(showMissionString is Mission) showMissionString else Mission()
        initMission(showMission,binding)
    }

    private fun initMission(showMission: Mission, binding: ActivityShowMissionBinding) {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        binding.deadlineTime.text = simpleDateFormat.format(showMission.deadlineTime)
        binding.missionContext.text = simpleDateFormat.format(showMission.missionContext)
        binding.missionTitle.setTitle(showMission.missionTitle)
        binding.missionTime.text = simpleDateFormat.format(showMission.missionTime)
        binding.missionType.text = showMission.missionType?.missionTypeTitle
        val users = StringBuffer()
        if(showMission.missionUser!=null)
        for (user in showMission.missionUser){
            users.append(user.personal?.name+" ")
        }
        binding.missionUsers.text = "任务接受人：${users}"
        binding.send.setOnClickListener {
            thread {
                showMission.missionState = true
                var mission = Gson().toJson(showMission)
                var add = FormBody.Builder().add("mission", mission).build()
                var post = ConnectWebUtil.getInstance()
                    .post(add, "${getString(R.string.host)}/mission/saveMission")
                runOnUiThread {
                    if (post == "true") Toast.makeText(this, "成功完成", Toast.LENGTH_LONG).show()
                    else Toast.makeText(this, "失败", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}