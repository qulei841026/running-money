package com.assassin.running.money.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.assassin.running.money.R

/**
 * Created by Le-q on 2017/12/21.
 */
class HomeExpectAdapter(context: Context) : RecyclerView.Adapter<HomeExpectAdapter.ViewHolder>() {

    private val mContext: Context = context

    private val items: MutableList<Int> = MutableList(10, { index -> index + 1 })

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.home_expect_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        init {

        }

    }

}