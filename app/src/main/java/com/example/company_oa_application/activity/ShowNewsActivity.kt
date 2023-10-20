package com.example.company_oa_application.activity

import android.os.Bundle
import com.example.company_oa_application.R
import com.example.company_oa_application.adapter.NewsAppendixFileItemAdapter
import com.example.company_oa_application.data.entity.newsAndAnnouncement.News
import com.example.company_oa_application.databinding.ActivityShowNewsBinding
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.text.SimpleDateFormat

class ShowNewsActivity : BaseActivity() {

    private lateinit var binding: ActivityShowNewsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShowNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val newsString= intent.getStringExtra("showNews")
        val type: Type = object : TypeToken<News?>() {}.type
        val news:News = Gson().fromJson(newsString, type)
        binding.showNewsTitle.text = news.newsDescribe
        setSupportActionBar(findViewById(R.id.toolbar))
        binding.newsTitle.subTitleText.text = news.newsTitle
        binding.newsNewsInformation.text = news.newsInformation
        binding.newsTitle.setLeftClickListener {
            onDestroy()
        }
        binding.newsCreateTime.text = SimpleDateFormat("yyyy-MM-dd").format(news.createTime)
        var appendixFiles = news.newsAppendixFiles!!
        var adapter = NewsAppendixFileItemAdapter(appendixFiles)
        binding.newsAppendixFileList.adapter = adapter

    }
}