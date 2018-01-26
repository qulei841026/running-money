package com.assassin.running.money.utils

import java.text.NumberFormat
import java.util.*

/**
 * NumberUtil
 *
 * Created by Qulit on 2018/1/10.
 */
object NumberUtil {

    const val MAX_INT = 9
    const val MAX_DECIMAL = 2
    const val MAX_PERCENT = 3

    private val rmbFormat = NumberFormat.getCurrencyInstance(Locale.CHINA)!!

    private val normalFormat = NumberFormat.getNumberInstance()

    init {
        normalFormat.minimumFractionDigits = 2
        normalFormat.maximumFractionDigits = 9
    }

    fun <T> formatRMB(num: Long, body: (s: String) -> T): T {
        val str = rmbFormat.format(num)
        return body(str)
    }

    fun formatNormal(num: Double): String {
        return normalFormat.format(num)
    }

}