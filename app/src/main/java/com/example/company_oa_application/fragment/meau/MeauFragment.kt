package com.example.company_oa_application.fragment.meau

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.company_oa_application.R
import com.example.company_oa_application.activity.*
import com.example.company_oa_application.adapter.ClickListener
import com.example.company_oa_application.fragment.meau.placeholder.PlaceholderItem

/**
 * A fragment representing a list of Items.
 */
class MeauFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meau, container, false)
        // Set the adapter
        val meaus  = arrayListOf(
            createContentButton(R.drawable.nav_01_pre, SignActivity::class.java,"签到"),
            createContentButton(R.drawable.nav_01_pre,NewsActivity::class.java,"新闻"),
            createContentButton(R.drawable.nav_01_pre,AnnouncementActivity::class.java,"通知"),
            createContentButton(R.drawable.nav_01_pre,InnerMailActivity::class.java,"内部邮件"),
            createContentButton(R.drawable.nav_01_pre,OuterMailActivity::class.java,"外部邮件"),
            createContentButton(R.drawable.nav_01_pre,MissionActivity::class.java,"任务")
        )

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MeauItemAdapter(meaus)
            }
        }
        return view
    }

    fun createContentButton(pic:Int, activity: Class<*>?,text:String):PlaceholderItem{
        var clickListener = ClickListener(activity)
        return PlaceholderItem(text,pic,clickListener)
    }
    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
        @JvmStatic
        fun newInstance(columnCount: Int) =
            MeauFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }


    }
}