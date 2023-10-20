package com.example.company_oa_application.activity

import android.os.Bundle
import android.widget.Toast
import com.example.company_oa_application.R
import com.example.company_oa_application.databinding.ActivityAddNewInnerMailBinding
import com.example.company_oa_application.entity.hrmResource.Personal
import com.example.company_oa_application.entity.hrmResource.User
import com.example.company_oa_application.entity.mail.innerMail.Mail
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.xuexiang.xui.widget.edittext.ClearEditText
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinner
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinnerAdapter
import okhttp3.FormBody
import java.lang.reflect.Type
import kotlin.concurrent.thread

class AddNewInnerMailActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_inner_mail)
        val newMail = Mail()
        val binding = ActivityAddNewInnerMailBinding.inflate(layoutInflater)
        var arrayList = ArrayList<Personal>()
        var serializableExtra = intent.getSerializableExtra("addressee")
            thread {
                var build = FormBody.Builder().build()
                if(serializableExtra!=null&&serializableExtra is Personal){
                    arrayList.add(serializableExtra)
                    val adapter = MaterialSpinnerAdapter(this, arrayList)
                    ActivityList.getThisActivity().runOnUiThread {
                        findViewById<MaterialSpinner>(R.id.addressee).apply {
                            isEnabled = false
                            setAdapter(adapter)
                        }
                    }
                }else{
                    val messages = ConnectWebUtil.getInstance()
                        .post(build, "${getString(R.string.host)}/getPersonal/getAllPersonals")
                    val type: Type = object : TypeToken<List<Personal?>?>() {}.type
                    if (messages != "")
                        arrayList.addAll(Gson().fromJson(messages, type))
                    val adapter = MaterialSpinnerAdapter(this, arrayList)
                    ActivityList.getThisActivity().runOnUiThread {
                        findViewById<MaterialSpinner>(R.id.addressee).apply {
                            setAdapter(adapter)
                        }
                    }
                }
                var arrayList2 = ArrayList<Mail>()
                val mails = ConnectWebUtil.getInstance().post(
                    build,
                    "${getString(R.string.host)}/innerMail/innerMail/getMailsByAddressee/2/0"
                )
                val type2: Type = object : TypeToken<List<Mail?>?>() {}.type
                if(mails!="")
                    arrayList2.addAll(Gson().fromJson(mails,type2))
                val adapter2 = MaterialSpinnerAdapter(this, arrayList2)
                ActivityList.getThisActivity().runOnUiThread {
                    findViewById<MaterialSpinner>(R.id.reply_from).apply {
                        setAdapter(adapter2)
                    }
                }

        }
        binding.addressee.setOnItemSelectedListener {
                view, position, id, item ->
            val personal = item as Personal
            newMail.addressee = personal.user
        }
        binding.replyFrom.setOnItemSelectedListener {
                view, position, id, item ->
            val mail = item as Mail
            newMail.replyFrom = mail
        }
        findViewById<ClearEditText>(R.id.addresser).setText("12345")
        initMail(binding,newMail)
    }
    private fun saveNewMail(binding: ActivityAddNewInnerMailBinding, newMail: Mail): String {
        newMail.mailTitle = binding.mailTitle.text.toString()
        newMail.mailSummary = binding.mailSummary.text.toString()
        newMail.mailContext = binding.mailContext.contentText
        newMail.addresser = MainActivity.loginPersonal!!.user
        return Gson().toJson(newMail)
    }
    private fun initMail(binding: ActivityAddNewInnerMailBinding, newMail: Mail) {
        binding.summitButton.setOnClickListener {
            val newMailJson = saveNewMail(binding,newMail)
            thread {
                var build = FormBody.Builder().add("mail",newMailJson).build()
                val messages= ConnectWebUtil.getInstance().post(build, "${getString(R.string.host)}/mail/sendMail")
                runOnUiThread {
                    if(messages!=""&&messages.equals("true"))
                        Toast.makeText(this,"发送成功",Toast.LENGTH_LONG).show()
                    else
                        Toast.makeText(this,"发送失败",Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.saveButton.setOnClickListener {
            val newMailJson = saveNewMail(binding,newMail)
            var build = FormBody.Builder().add("mail",newMailJson).build()
            val messages= ConnectWebUtil.getInstance().post(build, "${getString(R.string.host)}/innerMail/sendMail")
            runOnUiThread {
                if(messages!=""&&messages.equals("true"))
                    Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this,"保存失败",Toast.LENGTH_LONG).show()
            }
        }
    }
}