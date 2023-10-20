package com.example.company_oa_application.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.activity.ShowAnnouncementActivity
import com.example.company_oa_application.data.entity.newsAndAnnouncement.Announcement

import com.example.company_oa_application.databinding.FragmentAnnouncementListItemBinding
import com.google.gson.Gson
import java.text.SimpleDateFormat

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class AnnouncementItemAdapter(
    private var values: List<Announcement>
) : RecyclerView.Adapter<AnnouncementItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentAnnouncementListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        holder.announcementDepartment.text = item.announcementDepartment!!.departmentName
        holder.announcementTime.text =  dateFormat.format(item.announcementDate)
        holder.announcementTitle.text  = item.announcementTitle
        holder.announcementTitle.setOnClickListener {
            val thisActivity = ActivityList.getThisActivity()
            var intent = Intent(thisActivity, ShowAnnouncementActivity::class.java)
            val announcement: String? = Gson().toJson(item)
            intent.putExtra("showAnnouncement",announcement)
            thisActivity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size
    fun changeAnnouncement(announcements: ArrayList<Announcement>) {
        this.values = announcements
    }

    inner class ViewHolder(binding: FragmentAnnouncementListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val announcementDepartment: TextView = binding.announcementDepartment
        val announcementTime: TextView = binding.announcementTime
        val announcementTitle:TextView = binding.announcementTitle
    }

}