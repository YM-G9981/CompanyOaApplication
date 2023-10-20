package com.example.company_oa_application.adapter;

import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.company_oa_application.entity.hrmResource.Personal

import com.xuexiang.xui.R
import com.xuexiang.xui.widget.spinner.materialspinner.MaterialSpinnerAdapter

class MailAddresserAdapter(val context:Context ,val personals:  List<Personal> ): MaterialSpinnerAdapter<Personal>(context,personals) {
    override fun getItemText(position: Int): String {
        return personals[position].name!!
    }
}