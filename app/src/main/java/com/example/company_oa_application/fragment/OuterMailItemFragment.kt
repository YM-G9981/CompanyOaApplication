package com.example.company_oa_application.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.company_oa_application.R
import com.example.company_oa_application.adapter.OuterMailItemAdapter
import com.example.company_oa_application.entity.mail.outerMail.Mail

/**
 * A fragment representing a list of Items.
 */
class OuterMailItemFragment(val mailList: List<Mail>): Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_outer_mail_list, container, false)
        if (view is RecyclerView) {
            with(view) {
                view.adapter = OuterMailItemAdapter(mailList)
            }
        }
        return view
    }
}