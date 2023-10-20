package com.example.company_oa_application.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.company_oa_application.R
import com.example.company_oa_application.adapter.InnerMailItemAdapter
import com.example.company_oa_application.databinding.FragmentInnerMailListBinding
import com.example.company_oa_application.databinding.FragmentInnerMailListItemBinding
import com.example.company_oa_application.entity.mail.innerMail.Mail

/**
 * A fragment representing a list of Items.
 */
class InnerMailItemFragment(val mailList: List<Mail>): Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentInnerMailListBinding.inflate(inflater,container,false)
        binding.list.adapter = InnerMailItemAdapter(mailList)
        return binding.root
    }
}