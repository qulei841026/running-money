package com.assassin.running.money.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import com.assassin.running.money.R


/**
 * RecyclerViewUtil
 *
 * Created by Qulit on 2018/1/8.
 */
object RecyclerViewUtil {

    fun getCommonVerticalDivider(context: Context): RecyclerView.ItemDecoration {
        val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(context, R.drawable.common_recycler_view_divider)?.let { divider.setDrawable(it) }
        return divider
    }

}
