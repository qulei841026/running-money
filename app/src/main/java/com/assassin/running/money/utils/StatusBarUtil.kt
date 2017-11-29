package com.assassin.running.money.utils

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.view.WindowManager

/**

 * Created by Qulit on 2017/11/29.
 */
object StatusBarUtil {

    /**
     * 内嵌透明状态栏的全屏显示
     * 需要配合ToolBar根布局设置:android:fitsSystemWindows="false"
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    fun setStatusBarTransparentV19(window: Window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }

    /**
     * 内嵌透明状态栏的全屏显示
     * 需要配合ToolBar根布局设置:android:fitsSystemWindows="false"
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    fun setStatusBarTransparentV21(window: Window) {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.TRANSPARENT
    }

    /**
     * 获取状态栏高度
     */
    fun getStatusBarHeight(context: Context, default: Int): Int {
        var result = default
        val resourceId = context.resources
                .getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

}