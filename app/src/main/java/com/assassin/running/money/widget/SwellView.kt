package com.assassin.running.money.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * SwellView
 * Created by Qulit on 2017/12/6.
 */
class SwellView : View {

    companion object {
        val TAG = "SwellView"

        fun createPaint(): Paint {
            val paint = Paint()
            paint.color = Color.WHITE
            paint.isAntiAlias = true
            return paint
        }
    }

    private class MultiPoint(val sx: Float, val sy: Float, val ex: Float, val ey: Float,
                             val mx: Float, val my: Float) {
        fun getPath(): Path {
            val path = Path()
            path.moveTo(sx, sy)
            path.quadTo(mx, my, ex, ey)
            return path
        }
    }

    private val paint: Paint = createPaint()
    private var multiPoint: MultiPoint? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    fun debug(log: Any) = Log.d("qulei", "[$TAG]->$log")

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        debug("onLayout")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        debug("onMeasure")
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        debug("draw")
        if (multiPoint != null) {
            canvas!!.drawPath(multiPoint?.getPath(), paint)
        }
    }

    private var animator: ValueAnimator? = null

    fun start() {
        animator = ValueAnimator.ofInt(0, 100)
        animator?.duration = 3000
        animator?.interpolator = LinearInterpolator()
        animator?.addUpdateListener { animator ->
            val v = animator.animatedValue
            debug("v=$v")
            invalidate()
        }
        animator?.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (animator != null) {
            debug("animator cancel")
            animator?.cancel()
        }
    }


}