package com.example.company_oa_application.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.example.company_oa_application.databinding.ActivityPersonalBinding
import com.example.company_oa_application.entity.hrmResource.Personal
import com.xuexiang.xui.XUI.getContext
import java.text.SimpleDateFormat

class PersonalActivity : BaseActivity() {
    private lateinit var binding: ActivityPersonalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonalBinding.inflate(layoutInflater)
        val showPersonal = intent.getSerializableExtra("showPersonal")
        var personal = if(showPersonal is Personal) showPersonal else Personal()
        setContentView(binding.root)
        binding.fab.setOnClickListener {
            startActivity(Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:${personal.phone}")))
        }
        binding.sendMail.setOnClickListener {
            startActivity(Intent(this,AddNewInnerMailActivity::class.java).putExtra("addressee",showPersonal))
        }
        val format = SimpleDateFormat("yyyy-MM-dd")
        binding.apply {
            birthday.text =  "出生日期：${format.format(personal.birthday)}"
            personalName.setTitle(personal.name)
            jobNumber.text = personal.jobNumber
            sex.text = if(personal.sex==1)"男" else "女"
            age.text = "年龄：${personal.age}"
            hireDate.text = "入职日期：${format.format(personal.hireDate)}"
            idNumber.text = "身份证号：${personal.idnumber}"
            department.text = personal.department!!.departmentName
            superior.text = if(personal.superior!=null) "直接上级：${personal!!.superior!!.name}" else ""
            position.text = personal.position!!.positionName
            phone.text = "电话：${personal.phone}"
        }
    }
}