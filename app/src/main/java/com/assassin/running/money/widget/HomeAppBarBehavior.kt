package com.assassin.running.money.widget

import android.content.Context
import android.support.design.widget.AppBarLayout
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * HomeAppBarBehavior
 *
 * Created by Qulit on 2017/12/5.
 */
class HomeAppBarBehavior : AppBarLayout.Behavior {

    companion object {
        val TAG = "HomeAppBarBehavior"
        var logging = true
    }

    fun debug(log: Any) = Log.d("qulei", "[$TAG]->$log")

    constructor() : super()

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        debug("HomeAppBarBehavior constructor")
    }

    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
//        debug("onNestedPreScroll x : $dx, dy : $dy")
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }

    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, velocityX: Float, velocityY: Float): Boolean {
//        debug("onNestedPreFling")
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }

    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: AppBarLayout, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
//        debug("onNestedFling")
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    override fun onStartNestedScroll(parent: CoordinatorLayout, child: AppBarLayout, directTargetChild: View, target: View, nestedScrollAxes: Int, type: Int): Boolean {
//        debug("onStartNestedScroll")
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type)
    }

    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout, abl: AppBarLayout, target: View, type: Int) {
//        debug("onStopNestedScroll")
        super.onStopNestedScroll(coordinatorLayout, abl, target, type)
    }

}