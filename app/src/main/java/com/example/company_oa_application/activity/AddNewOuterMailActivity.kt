package com.example.company_oa_application.activity

import android.os.Bundle
import android.widget.Toast
import com.example.company_oa_application.R
import com.example.company_oa_application.databinding.ActivityAddNewOuterMailBinding
import com.example.company_oa_application.entity.hrmResource.Personal
import com.example.company_oa_application.entity.mail.outerMail.Mail
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import com.xuexiang.xui.widget.edittext.ClearEditText
import okhttp3.FormBody
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class AddNewOuterMailActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_outer_mail)
        val newMail = Mail()
        val binding = ActivityAddNewOuterMailBinding.inflate(layoutInflater)
        findViewById<ClearEditText>(R.id.addresser).setText("12345")
        initMail(binding,newMail)
    }
        private fun saveNewMail(binding: ActivityAddNewOuterMailBinding, newMail: Mail): String {
            newMail.subject = binding.mailTitle.text.toString()
            newMail.sendTime =  Date(System.currentTimeMillis())
            newMail.mailState = 1
            newMail.sendTo = binding.sendTo.text.toString()
            newMail.text = binding.mailContext.contentText
        return Gson().toJson(newMail)
    }
    private fun initMail(binding: ActivityAddNewOuterMailBinding, newMail: Mail) {
        binding.summitButton.setOnClickListener {
            val newMailJson = saveNewMail(binding, newMail)
            thread {
                var build = FormBody.Builder().add("mail", newMailJson).build()
                val messages = ConnectWebUtil.getInstance()
                    .post(build, "${getString(R.string.host)}/outerMail/sendMail")
                runOnUiThread {
                    if (messages != "" && messages.equals("true"))
                        Toast.makeText(this, "发送成功", Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this, "发送失败", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}