package com.assassin.running.money.widget

import android.animation.ValueAnimator
import android.annotation.TargetApi
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

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

    private val paint: Paint = createPaint()
    private val drawPath = Path()
    private val points = Array(7, { Point() })

    private var incHeight: Int = 0
    private var peak: Int = 100
    private var trough: Int = 60
    private var shift: Int = 0

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr)

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) :
            super(context, attrs, defStyleAttr, defStyleRes)

    fun debug(log: Any) = Log.d("qulei", "[$TAG]->$log")

    init {
        debug("points:" + points.size)
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        debug("onLayout==($width,$height)")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        debug("onMeasure")
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas?.drawPath(drawPath, paint)
    }

    private inline fun drewPath(body: () -> Unit): Path {
        body()
        val w = width.toFloat()
        val h = height.toFloat()
        val dh = if (incHeight > h) h.toInt() else incHeight
        drawPath.reset()
        drawPath.moveTo(0f, h)
        drawPath.lineTo(0f, h - dh)
        drawPath.quadTo(w / 4, h - dh - peak, w / 2, h - dh)
        drawPath.quadTo(w * 3 / 4, h - dh + trough, w, h - dh)
        drawPath.lineTo(w, h)
        drawPath.close()
        return drawPath
    }

    private var animator: ValueAnimator? = null

    fun start() {
        animator = ValueAnimator.ofInt(0, 100)
        animator?.duration = 2000
        animator?.interpolator = AccelerateDecelerateInterpolator()
        animator?.addUpdateListener { animator ->
            val value = animator.animatedValue as Int
            drewPath({
                incHeight = value * 2
                invalidate()
            })
            debug("v1=$value")
        }
        animator?.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        debug("animator cancel")
        animator?.cancel()

    }

}