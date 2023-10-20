package com.example.company_oa_application.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.company_oa_application.R
import com.example.company_oa_application.activity.BaseActivity
import com.example.company_oa_application.databinding.ActivityShowInnerMailBinding
import com.example.company_oa_application.entity.mail.innerMail.Mail
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import okhttp3.FormBody
import java.text.DateFormat
import java.text.SimpleDateFormat
import kotlin.concurrent.thread

class ShowInnerMailActivity : BaseActivity() {

    private lateinit var binding: ActivityShowInnerMailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityShowInnerMailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarLayout.title = title
        var showInnerMailString = intent.getSerializableExtra("showInnerMail")
        var showInnerMail:Mail = if(showInnerMailString is Mail) showInnerMailString else Mail()
        initInnerMail(showInnerMail,binding)
    }

    private fun initInnerMail(showInnerMail: Mail, binding: ActivityShowInnerMailBinding) {
        binding.addressee.text = showInnerMail.addressee!!.personal!!.name
        binding.addresser.text = showInnerMail.addresser!!.personal!!.name
        binding.mailContext.text = showInnerMail.mailContext
        binding.replyFrom.text = if(showInnerMail.replyFrom!=null) showInnerMail.replyFrom!!.mailTitle else ""
        binding.mailSummary.text = showInnerMail.mailSummary
        binding.mailTitle.setTitle(showInnerMail.mailTitle)
        binding.sendTime.text  = SimpleDateFormat("yyyy-MM-dd").format(showInnerMail.sendTime)
        binding.sender.isClickable = showInnerMail.mailState==0
        binding.sender.setOnClickListener {
            thread {
                var mail = Gson().toJson(showInnerMail)
                var add = FormBody.Builder().add("mail", mail).build()
                var post = ConnectWebUtil.getInstance()
                    .post(add, "${getString(R.string.host)}/innerMail/sendMail")
                runOnUiThread {
                    if (post == "true") Toast.makeText(this, "发送成功", Toast.LENGTH_LONG).show()
                    else Toast.makeText(this, "发送失败", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}