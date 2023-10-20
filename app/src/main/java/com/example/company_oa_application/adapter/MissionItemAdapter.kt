package com.example.company_oa_application.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.activity.ShowInnerMailActivity

import com.example.company_oa_application.databinding.FragmentMissionListItemBinding
import com.example.company_oa_application.entity.mission.Mission
import java.text.SimpleDateFormat

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MissionItemAdapter(
    private var values: List<Mission>
) : RecyclerView.Adapter<MissionItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMissionListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = values[position]
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd")
        holder.deadlineTime.text = simpleDateFormat.format(value.deadlineTime)
        holder.missionTime.text = simpleDateFormat.format(value.missionTime)
        holder.missionType.text = value.missionType!!.missionTypeTitle
        holder.missionTitle.text = value.missionTitle
        holder.missionTitle.setOnClickListener {
            val intent = Intent(ActivityList.getThisActivity(), ShowInnerMailActivity::class.java)
            intent.putExtra("showMission",value)
            ActivityList.getThisActivity().startActivity(intent)
        }

    }

    override fun getItemCount(): Int = values.size
    fun changeMissions(missions: List<Mission>) {
        this.values = missions
    }

    inner class ViewHolder(binding: FragmentMissionListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val missionType : TextView = binding.missionType
        val missionTitle : TextView = binding.missionTitle
        val missionTime : TextView = binding.missionTime
        val deadlineTime : TextView = binding.deadlineTime
    }

}