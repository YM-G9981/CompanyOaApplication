package com.example.company_oa_application.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.company_oa_application.R
import com.example.company_oa_application.adapter.FragmentAdapter
import com.example.company_oa_application.databinding.ActivityOuterMailBinding
import com.example.company_oa_application.entity.mail.outerMail.Mail
import com.example.company_oa_application.fragment.OuterMailItemFragment
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.FormBody
import java.lang.reflect.Type
import kotlin.concurrent.thread

class OuterMailActivity : BaseActivity() {
    private lateinit var binding: ActivityOuterMailBinding
    private val connectWebUtil = ConnectWebUtil.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOuterMailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val paper = binding.viewMail
        binding.mailTitle.setLeftClickListener {
            super.onDestroy()
        }
        initViewPaper(paper)
        binding.addNewMail.setOnClickListener {
            startActivity(Intent(this,AddNewOuterMailActivity::class.java))
        }
    }

    private fun initViewPaper(paper: ViewPager2) {
        thread {
            val fragmentList = ArrayList<Fragment>()
            fragmentList.apply {
                add(OuterMailItemFragment(initMails("/outerMail/getMailsBySender/3")))//草稿箱
             }
            var adapter = FragmentAdapter(this, fragmentList)
            this.runOnUiThread {
                paper.adapter = adapter
            }
        }
    }
    private fun initMails(address:String):List<Mail>{
        val mails = ArrayList<Mail>()
        val host = getString(R.string.host) + address
        val build = FormBody.Builder().build()
        val messages = connectWebUtil.post(build, host)
        val type: Type = object : TypeToken<List<Mail?>?>() {}.type
        if (messages != "")
            mails.addAll(Gson().fromJson(messages, type))
        return mails
    }
}
