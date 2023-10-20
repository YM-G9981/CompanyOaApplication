package com.example.company_oa_application.adapter

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView

import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.activity.ShowInnerMailActivity
import com.example.company_oa_application.databinding.FragmentInnerMailListItemBinding
import com.example.company_oa_application.entity.mail.innerMail.Mail
/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class InnerMailItemAdapter(
    private val values: List<Mail>
) : RecyclerView.Adapter<InnerMailItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentInnerMailListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val value = values[position]
        holder.mailReceiver.text = value.addressee!!.personal!!.name
        holder.mailSender.text = value.addresser!!.personal!!.name
        holder.mailTitle.text = value.mailTitle
        holder.mailSummary.text = value.mailSummary
        holder.mailTitle.setOnClickListener {
            val intent = Intent(ActivityList.getThisActivity(), ShowInnerMailActivity::class.java)
            intent.putExtra("showInnerMail",value)
            ActivityList.getThisActivity().startActivity(intent)
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentInnerMailListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val mailTitle : TextView = binding.mailTitle
        val mailSummary : TextView = binding.mailSummary
        val mailSender :TextView = binding.mailSender
        val mailReceiver :TextView = binding.mailReceiver
    }

}