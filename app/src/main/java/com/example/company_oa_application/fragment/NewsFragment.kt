package com.example.company_oa_application.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.company_oa_application.adapter.NewsItemAdapter
import com.example.company_oa_application.R
import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.data.entity.newsAndAnnouncement.News
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.FormBody
import java.lang.reflect.Type
import kotlin.concurrent.thread

/**
 * A fragment representing a list of Items.
 */
class NewsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var news =  ArrayList<News>()
        val connectWebUtil = ConnectWebUtil.getInstance()
        val view = inflater.inflate(R.layout.fragment_news_list, container, false)
        var adapter = NewsItemAdapter(news)
        if (view is RecyclerView) {
            view.adapter = adapter
        }
        thread {
            val host = getString(R.string.host) + "/news/getNewsByNewsState/2"
            val build = FormBody.Builder().build()
            val messages = connectWebUtil.post(build,host)
            val type: Type = object : TypeToken<List<News?>?>() {}.type
            if (messages != "")
                news = Gson().fromJson(messages,type)
            adapter.changeNews(news)
            ActivityList.getThisActivity().runOnUiThread {
                adapter.notifyDataSetChanged()
                if(view is RecyclerView){
                    view.adapter = adapter
                }
            }
        }

        return view
    }
}