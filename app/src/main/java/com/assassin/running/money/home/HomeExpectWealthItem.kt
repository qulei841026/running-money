package com.assassin.running.money.home

import com.assassin.running.money.MainApp
import com.assassin.running.money.R
import com.assassin.running.money.dto.ExpectWealthDto
import com.assassin.running.money.utils.NumberUtil

/**
 * HomeExpectWealthItem
 * Created by Qulit 2018/1/8.
 */
class HomeExpectWealthItem(private val dto: ExpectWealthDto) {

    fun textTitle(): String {
        return dto.title
    }

    fun textIncrease(): String {
        val context = MainApp.getInstance().applicationContext
        val increase = try {
            dto.increase.toFloat() / 100
        } catch (e: Exception) {
            0f
        }
        return when (increase) {
            0f -> context.getString(R.string.no_set_expect)
            else -> "${context.getString(R.string.expect_increase_field, increase.toString())}%"
        }
    }

    fun textPlanMoney(): String {
        val context = MainApp.getInstance().applicationContext
        return try {
            val money = dto.planMoney.toLong()
            NumberUtil.formatRMB(money, { context.getString(R.string.plan_money_field, it) })
        } catch (e: Exception) {
            context.getString(R.string.no_set_plan)
        }
    }

    fun textTotalMoney(): String {
        val context = MainApp.getInstance().applicationContext
        return try {
            val money = dto.totalMoney.toLong()
            if (money == 0L) context.getString(R.string.nothing)
            else NumberUtil.formatRMB(money, { it })
        } catch (e: Exception) {
            context.getString(R.string.nothing)
        }
    }


}