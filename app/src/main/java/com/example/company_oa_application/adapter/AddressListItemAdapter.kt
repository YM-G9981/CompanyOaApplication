package com.example.company_oa_application.adapter

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.activity.PersonalActivity
import com.example.company_oa_application.databinding.FragmentAddressListItemBinding

import com.example.company_oa_application.entity.hrmResource.Personal

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class AddressListItemAdapter(
    private var personals: List<Personal>
) : RecyclerView.Adapter<AddressListItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentAddressListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val personal = personals[position]
        holder.sex.text = if(personal.sex == 1) "男" else "女"
        holder.jobNumber.text = personal.jobNumber
        holder.name.text = personal.name
        holder.phone.text = personal.phone
        holder.name.setOnClickListener {
            var personal = personals[position]
            val intent = Intent(ActivityList.getThisActivity(), PersonalActivity::class.java).apply {
                putExtra("showPersonal", personal)
            }
            ActivityList.getThisActivity().startActivity(intent)
        }
    }

    override fun getItemCount(): Int = personals.size

    inner class ViewHolder(binding: FragmentAddressListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val jobNumber: TextView = binding.jobNumber
        val name: TextView = binding.name
        val sex : TextView = binding.sex
        val phone : TextView = binding.phone
    }
    fun changePersonals(personals: List<Personal>){
        this.personals = personals
    }
}