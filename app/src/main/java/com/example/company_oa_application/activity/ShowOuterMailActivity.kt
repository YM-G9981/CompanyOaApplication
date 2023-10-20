package com.example.company_oa_application.activity

import android.os.Bundle
import android.widget.Toast
import com.example.company_oa_application.R
import com.example.company_oa_application.databinding.ActivityShowOuterMailBinding
import com.example.company_oa_application.entity.mail.outerMail.Mail
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import okhttp3.FormBody
import java.text.DateFormat
import java.text.SimpleDateFormat
import kotlin.concurrent.thread

class ShowOuterMailActivity : BaseActivity() {

    private lateinit var binding: ActivityShowOuterMailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowOuterMailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarLayout.title = title
        var showOuterMailString = intent.getSerializableExtra("showOuterMail")
        var showOuterMail:Mail = if(showOuterMailString is Mail) showOuterMailString else Mail()
        initInnerMail(showOuterMail,binding)
    }

    private fun initInnerMail(showOuterMail: Mail, binding: ActivityShowOuterMailBinding) {
        binding.mailSender.text = showOuterMail.user!!.personal!!.name
        binding.mailText.text = showOuterMail.text
        binding.sendTime.text = SimpleDateFormat("yyyy-MM-dd").format(showOuterMail.sendTime)
        binding.sendTo.text = showOuterMail.sendTo
        binding.mailTitle.setTitle(showOuterMail.subject)
    }
}