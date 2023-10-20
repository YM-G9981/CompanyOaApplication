package com.example.company_oa_application.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.company_oa_application.R
import com.example.company_oa_application.activity.MainActivity
import com.example.company_oa_application.databinding.FragmentMessageSettingBinding
import java.text.SimpleDateFormat

class FragmentMessageSetting : Fragment() {
  private lateinit var binding: FragmentMessageSettingBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMessageSettingBinding.inflate(inflater,container,false)
    return binding.root
    }

    override fun onStart() {
        super.onStart()
        var personal = MainActivity.loginPersonal
        val format = SimpleDateFormat("yyyy-MM-dd")
        if(personal!=null) {

            binding.apply {
                birthday.text = "出生日期：${format.format(personal.birthday)}"
                personalName.setTitle(personal.name)
                jobNumber.text = personal.jobNumber
                sex.text = if (personal.sex == 1) "男" else "女"
                age.text = "年龄：${personal.age}"
                hireDate.text = "入职日期：${format.format(personal.hireDate)}"
                idNumber.text = "身份证号：${personal.idnumber}"
                department.text = personal.department!!.departmentName
                superior.text =
                    if (personal.superior != null) "直接上级：${personal!!.superior!!.name}" else ""
                position.text = personal.position!!.positionName
                phone.text = "电话：${personal.phone}"
            }
        }
    }
}