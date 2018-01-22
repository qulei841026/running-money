package com.assassin.running.money.utils

import java.text.NumberFormat
import java.util.*

/**
 * NumberUtil
 *
 * Created by Qulit on 2018/1/10.
 */
object NumberUtil {

    const val MAX: Int = 999999999

    private val rmbFormat = NumberFormat.getCurrencyInstance(Locale.CHINA)!!

    private val normalFormat = NumberFormat.getNumberInstance()

    fun <T> formatRMB(num: Long, body: (s: String) -> T): T {
        val str = rmbFormat.format(num)
        return body(str)
    }

    fun formatNormal(num: Long): String {
        return normalFormat.format(num)
    }

}