package com.example.company_oa_application.fragment.meau

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.example.company_oa_application.databinding.FragmentMeauItemBinding
import com.example.company_oa_application.fragment.meau.placeholder.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MeauItemAdapter(
    private val values: List<PlaceholderItem>
) : RecyclerView.Adapter<MeauItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentMeauItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.icon.setBackgroundResource(item.iconResource)
        holder.icon.setOnClickListener(item.onClickListener)
        holder.title.text = item.title
    }
    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentMeauItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.title
        var icon : ImageButton= binding.icon
    }

}