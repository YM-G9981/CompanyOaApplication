package com.example.company_oa_application.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.company_oa_application.R
import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.data.entity.newsAndAnnouncement.NewsAppendixFile
import com.example.company_oa_application.databinding.FragmentNewsFileListItemBinding
import com.example.company_oa_application.utils.ConnectWebUtil

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class NewsAppendixFileItemAdapter(
    private var values: List<NewsAppendixFile>
) : RecyclerView.Adapter<NewsAppendixFileItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentNewsFileListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.fileButton.setOnClickListener {
            Toast.makeText(ActivityList.getThisActivity(),"开始下载", Toast.LENGTH_SHORT).show()
            val url:String = ActivityList.getThisActivity().getString(R.string.host)+"/news/getFile/${item.fileId}"
            ConnectWebUtil.getInstance().downLoad(url,item.name)
        }
        holder.fileName.text = item.name
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentNewsFileListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val fileButton :ImageButton = binding.fileButton
        val fileName : TextView = binding.fileName
    }
    fun changeAppendixFiles(appendixFiles: List<NewsAppendixFile>){
        values = appendixFiles
    }
}