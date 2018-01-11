package com.assassin.running.money.home

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.assassin.running.money.R
import com.assassin.running.money.dto.ExpectWealthDto

/**
 * HomeExpectWealthAdapter
 * Created by Qulit on 2017/12/21.
 */
class HomeExpectWealthAdapter(context: Context) : RecyclerView.Adapter<HomeExpectWealthAdapter.HomeExpectViewHolder>() {

    private val mContext: Context = context
    private val items = mutableListOf<ExpectWealthDto>()

    fun addAll(collection: Collection<ExpectWealthDto>) {
        items.addAll(0, collection)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): HomeExpectWealthAdapter.HomeExpectViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.home_expect_wealth_layout, parent, false)
        return HomeExpectViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: HomeExpectWealthAdapter.HomeExpectViewHolder?, position: Int) {
        val vo = HomeExpectWealthItem(items[position])
        with(vo) {
            holder?.apply {
                tvTitle?.text = textTitle()
                tvIncrease?.text = textIncrease()
                tvPlanMoney?.text = textPlanMoney()
                tvTotalMoney?.text = textTotalMoney()
            }
        }
    }

    class HomeExpectViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView? = null
        var tvIncrease: TextView? = null
        var tvPlanMoney: TextView? = null
        var tvTotalMoney: TextView? = null

        init {
            itemView?.apply {
                tvTitle = findViewById(R.id.tv_home_expect_wealth_title)
                tvIncrease = findViewById(R.id.tv_home_expect_wealth_increase)
                tvPlanMoney = findViewById(R.id.tv_home_expect_wealth_plan_money)
                tvTotalMoney = findViewById(R.id.tv_home_expect_wealth_total_money)
            }

        }
    }

}