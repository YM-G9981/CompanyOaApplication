package com.example.company_oa_application.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.company_oa_application.R
import com.example.company_oa_application.activity.ActivityList
import com.example.company_oa_application.adapter.AddressListItemAdapter
import com.example.company_oa_application.databinding.FragmentAddressListBinding
import com.example.company_oa_application.entity.hrmResource.Personal
import com.example.company_oa_application.utils.ConnectWebUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.FormBody
import java.lang.reflect.Type
import kotlin.concurrent.thread


/**
 * A fragment representing a list of Items.
 */
class AddressListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddressListBinding.inflate(inflater, container, false)
        val connectWebUtil = ConnectWebUtil.getInstance()
            var personals: List<Personal> = ArrayList()
            var adapter = AddressListItemAdapter(personals)
            binding.addressList.adapter = adapter
            thread {
                val host = getString(R.string.host) + "/getPersonal/getAllPersonals"
                val build = FormBody.Builder().build()
                val messages = connectWebUtil.post(build,host)
                val type: Type = object : TypeToken<List<Personal?>?>() {}.type
                val personals:List<Personal> = Gson().fromJson(messages, type)
                if (messages != "")
                    adapter.changePersonals(personals)
                ActivityList.getThisActivity().runOnUiThread {
                    adapter.notifyDataSetChanged()
                }
            }
        return binding.root
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            AddressListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}