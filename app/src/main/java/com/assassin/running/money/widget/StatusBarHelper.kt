package com.assassin.running.money.widget

import android.os.Build
import android.support.v7.widget.Toolbar
import android.view.ViewGroup
import android.view.Window
import com.assassin.running.money.utils.StatusBarUtil

/**
 * StatusBarHelper
 *
 * Created by Qulit on 2017/11/29.
 */
class StatusBarHelper {

    companion object {
        private val sImpl = StatusBarHelperDerived(when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> StatusBarHelperLollipopImpl()
            Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT -> StatusBarHelperKitkatImpl()
            else -> StatusBarHelperSubImpl()
        })

        fun setStatusBarTransparent(window: Window) {
            sImpl.setStatusBarTransparent(window)
        }

        fun setStatusBarMarginTop(toolbar: Toolbar, default: Int) {
            sImpl.setStatusBarMarginTop(toolbar, default)
        }

    }

    class StatusBarHelperDerived(private val impl: StatusBarHelperVersionImpl) :
            StatusBarHelperVersionImpl by impl {

        fun setStatusBarMarginTop(toolbar: Toolbar, default: Int) {
            if (impl.isValid && toolbar.layoutParams is ViewGroup.MarginLayoutParams) {
                val h = StatusBarUtil.getStatusBarHeight(toolbar.context, default)
                val mlp = toolbar.layoutParams as ViewGroup.MarginLayoutParams
                mlp.topMargin = h
            }
        }
    }

    class StatusBarHelperKitkatImpl : StatusBarHelperVersionImpl {
        override val isValid: Boolean
            get() = true

        override fun setStatusBarTransparent(window: Window) {
            StatusBarUtil.setStatusBarTransparentV19(window)
        }
    }

    class StatusBarHelperLollipopImpl : StatusBarHelperVersionImpl {
        override val isValid: Boolean = true

        override fun setStatusBarTransparent(window: Window) {
            StatusBarUtil.setStatusBarTransparentV21(window)
        }
    }

    class StatusBarHelperSubImpl : StatusBarHelperVersionImpl {
        override val isValid: Boolean = false
        override fun setStatusBarTransparent(window: Window) {

        }
    }

    interface StatusBarHelperVersionImpl {
        val isValid: Boolean
        fun setStatusBarTransparent(window: Window)
    }

}

