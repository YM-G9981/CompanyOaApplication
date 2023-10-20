package com.example.company_oa_application.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.activity.ShowOuterMailActivity
import com.example.company_oa_application.databinding.FragmentOuterMailListItemBinding
import com.example.company_oa_application.entity.mail.outerMail.Mail
import java.text.SimpleDateFormat

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class OuterMailItemAdapter(
    private val values: List<Mail>
) : RecyclerView.Adapter<OuterMailItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentOuterMailListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = values[position]
        holder.mailReceiver.text = value.sendTo
        holder.mailSender.text = value.user!!.personal!!.name
        holder.mailTitle.text = value.subject
        holder.sendTime.text = SimpleDateFormat("yyyy-MM-dd").format(value.sendTime)
        holder.mailTitle.setOnClickListener {
            val intent = Intent(ActivityList.getThisActivity(),ShowOuterMailActivity::class.java)
            intent.putExtra("showOuterMail",value)
            ActivityList.getThisActivity().startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentOuterMailListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val mailTitle : TextView = binding.mailTitle
        val mailSender :TextView = binding.mailSender
        val mailReceiver :TextView = binding.mailReceiver
        val sendTime :TextView = binding.sendTime
    }

}