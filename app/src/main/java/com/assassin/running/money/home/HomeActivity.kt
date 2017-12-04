package com.assassin.running.money.home

import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.assassin.running.money.R
import com.assassin.running.money.widget.StatusBarHelper
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.home_app_bar.*

/**
 * HomeActivity
 * Created by Qulit on 2017/11/27.
 */
class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        setSupportActionBar(toolbar_home)

        System.gc()
        val default = resources.getDimensionPixelSize(R.dimen.status_bar_default_height)
        StatusBarHelper.setStatusBarTransparent(window)
        StatusBarHelper.setStatusBarMarginTop(toolbar = toolbar_home, default = default)

        val toggle = ActionBarDrawerToggle(this, drawer_layout_home, toolbar_home,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_home.addDrawerListener(toggle)
        toggle.syncState()

        fab.setOnClickListener({
            Log.i("qulei", "height = " + toolbar_home.height)
        })

    }


}