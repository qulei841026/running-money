package com.assassin.running.money.utils

import java.text.NumberFormat
import java.util.*

/**
 * NumberUtil
 * Created by Qulit on 2018/1/10.
 */
object NumberUtil {

    @Suppress("MemberVisibilityCanPrivate")
    val rmbFormat = NumberFormat.getCurrencyInstance(Locale.CHINA)!!

    inline fun <T> formatRMB(num: Long, body: (s: String) -> T): T {
        val str = rmbFormat.format(num)
        return body(str)
    }

}