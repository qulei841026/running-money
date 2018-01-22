package com.assassin.running.money.members.ma

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.assassin.running.money.R
import com.assassin.running.money.utils.EditTextUtil
import com.assassin.running.money.widget.NumberKeyboard
import kotlinx.android.synthetic.main.money_alloc_activity.*

/**
 * MoneyAllocActivity
 * Created by Qulit on 2018/1/15.
 */
class MoneyAllocActivity : AppCompatActivity() {

    private var numberKeyboard: NumberKeyboard? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.money_alloc_activity)
        supportActionBar?.setTitle(R.string.money_alloc)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        numberKeyboard = NumberKeyboard(applicationContext, keyboard_view)
        numberKeyboard?.output = et_ma_total_money

        etExpectIncome.customSelectionActionModeCallback = EditTextUtil.disableActionCallback()

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }


}