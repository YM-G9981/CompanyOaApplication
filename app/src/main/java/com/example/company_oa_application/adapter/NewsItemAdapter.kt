package com.example.company_oa_application.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.company_oa_application.activity.ShowNewsActivity
import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.data.entity.newsAndAnnouncement.News

import com.example.company_oa_application.databinding.FragmentNewsListItemBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class NewsItemAdapter(
    private var values: List<News>
) : RecyclerView.Adapter<NewsItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentNewsListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        holder.createTime.text = dateFormat.format( item.createTime)
        holder.describe.text =  item.newsDescribe
        holder.newsTitle.text = item.newsTitle
        holder.newsTitle.setOnClickListener {
            val thisActivity = ActivityList.getThisActivity()
            var intent = Intent(thisActivity, ShowNewsActivity::class.java)
            val news: String? = Gson().toJson(item)
            intent.putExtra("showNews",news)
            thisActivity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentNewsListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val newsTitle: TextView = binding.newsTitle
        val createTime: TextView = binding.createTime
        val describe: TextView = binding.newsDescribe
    }
    fun changeNews(list: List<News>){
        values = list
    }

}