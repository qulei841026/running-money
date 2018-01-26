package com.assassin.running.money.widget

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.Log
import android.view.View
import android.widget.TextView
import com.assassin.running.money.R
import com.assassin.running.money.utils.NumberUtil


/**
 * NumberKeyboard
 * Created by Qulit on 2018/1/19.
 */
class NumberKeyboard(context: Context, private val keyboardView: KeyboardView) {

    private val regex = "^[0-9]+[.]\$".toRegex()
    private val animator: ObjectAnimator = ObjectAnimator
            .ofFloat(keyboardView, "translationY", 0f, 0f)
            .setDuration(400)

    private val animDismissListener: Animator.AnimatorListener = object : Animator.AnimatorListener {
        override fun onAnimationRepeat(animation: Animator?) {
        }

        override fun onAnimationEnd(animation: Animator?) {
            debug("onAnimationEnd")
            reset()
            animation?.removeListener(this)
        }

        override fun onAnimationCancel(animation: Animator?) {
        }

        override fun onAnimationStart(animation: Animator?) {
        }
    }

    var maxInt = NumberUtil.MAX_INT
    var suffix = ""

    companion object {
        const val TAG = "NumberKeyboard"
    }

    fun debug(log: Any) = Log.d("qulei", "[$TAG]->$log")

    var output: TextView? = null
        set(value) {
            field = value
            field?.apply {
                setTag(R.id.number_keyboard_out_object, StringBuilder(""))
            }
        }

    init {
        keyboardView.keyboard = Keyboard(context, R.xml.number)
        keyboardView.isPreviewEnabled = false
        keyboardView.visibility = View.INVISIBLE
        keyboardView.setOnKeyboardActionListener(object : KeyboardView.OnKeyboardActionListener {
            override fun onPress(primaryCode: Int) {
            }

            override fun onRelease(primaryCode: Int) {
            }

            override fun swipeLeft() {
            }

            override fun swipeRight() {
            }

            override fun swipeUp() {
            }

            override fun swipeDown() {
            }

            override fun onText(text: CharSequence?) {
            }

            override fun onKey(primaryCode: Int, keyCodes: IntArray?) {
                solveKeyCode(primaryCode)
            }
        })
    }

    fun showKeyboard() {
        with(animator) {
            if (isRunning) {
                cancel()
                reset()
            }
            keyboardView.visibility = View.VISIBLE
            setFloatValues(keyboardView.height.toFloat(), 0f)
            start()
        }
    }

    fun dismissKeyboard() {
        if (!isShow())
            return

        with(animator) {
            if (isRunning) {
                cancel()
            }
            addListener(animDismissListener)
            setFloatValues(0f, keyboardView.height.toFloat())
            start()
        }
    }

    fun isShow(): Boolean {
        return keyboardView.visibility == View.VISIBLE
    }

    private fun reset() {
        suffix = ""
        maxInt = NumberUtil.MAX_INT
        keyboardView.visibility = View.GONE
    }

    private fun solveKeyCode(primaryCode: Int) {

        val sb: StringBuilder = output?.getTag(R.id.number_keyboard_out_object) as StringBuilder

        when (primaryCode) {
            Keyboard.KEYCODE_DELETE -> {
                output?.let {
                    if (!sb.isEmpty()) {
                        if (regex.matches(sb.toString())) { //小数点结尾
                            sb.delete(sb.length - 2, sb.length)
                        } else {
                            sb.delete(sb.length - 1, sb.length)
                        }
                        fillText(it, sb.toString())
                    }
                }
            }
            in 49..57 -> {
                output?.let {
                    if (verifyInput(sb, { sb.append(primaryCode.toChar()) })) {
                        fillText(it, sb.toString())
                    }
                }
            }
            48 -> {
                output?.let {
                    if (!sb.isEmpty() && sb.toString() != "0"
                            && verifyInput(sb, { sb.append('0') })) {
                        fillText(it, sb.toString())
                        if (sb.toString() == "0.00")
                            sb.delete(0, sb.length)
                    }
                }
            }
            46 -> {
                output?.let {
                    if (sb.isEmpty()) {
                        sb.append("0.")
                    } else if (sb.toString() == "0" || !sb.contains('.')) {
                        sb.append('.')
                    }
                    fillText(it, sb.toString())
                }
            }
        }
    }

    private fun fillText(tv: TextView, text: String) {
        debug("text = $text")
        val number = try {
            text.toDouble()
        } catch (e: Exception) {
            0.00
        }
        val str = NumberUtil.formatNormal(number)
        debug("str = $str")
        tv.setTag(R.id.number_keyboard_out_value, number)
        tv.text = StringBuilder().append(str).append(suffix).toString()
    }

    private fun verifyInput(target: StringBuilder, body: () -> Unit): Boolean {
        val l = target.length
        val max = NumberUtil.MAX_INT + NumberUtil.MAX_DECIMAL + 1

        if (l == max)
            return false

        if (target.contains('.')) {
            val s = l - target.indexOf('.') - 1
            return if (s == NumberUtil.MAX_DECIMAL)  //小数位
                false
            else {
                body()
                true
            }
        } else {
            return if (l == maxInt)  //整数位
                false
            else {
                body()
                true
            }
        }
    }
}
