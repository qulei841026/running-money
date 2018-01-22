package com.assassin.running.money.widget

import android.content.Context
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.util.Log
import android.widget.TextView
import com.assassin.running.money.R
import com.assassin.running.money.utils.NumberUtil

/**
 * NumberKeyboard
 * Created by Qulit on 2018/1/19.
 */
class NumberKeyboard(context: Context, keyboardView: KeyboardView) {

    var output: TextView? = null
        set(value) {
            field = value
            field?.let {
                it.setTag(R.id.number_keyboard_out_object, it.text.toString().toLong())
            }
        }

    companion object {
        const val TAG = "NumberKeyboard"
    }

    fun debug(log: Any) = Log.d("qulei", "[$TAG]->$log")

    init {
        keyboardView.keyboard = Keyboard(context, R.xml.number)
        keyboardView.isPreviewEnabled = false
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

    private fun solveKeyCode(primaryCode: Int) {
        when (primaryCode) {
            Keyboard.KEYCODE_DELETE -> debug("delete")
            in 48..57 -> output?.let {
                val c = calculateInt(primaryCode, { it.getTag(R.id.number_keyboard_out_object) as Long })
                debug("c = $c")
                it.setTag(R.id.number_keyboard_out_object, c)
                it.text = NumberUtil.formatNormal(c)
            }
        }
    }

    private inline fun calculateInt(primaryCode: Int, body: () -> Long): Long {
        return try {
            val curNum = body()
            return if (curNum > NumberUtil.MAX) {
                curNum
            } else {
                val value = (curNum * 10) + (primaryCode - 48)
                value
            }
        } catch (e: Exception) {
            0
        }
    }

}