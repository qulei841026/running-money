package com.assassin.running.money.home

import android.content.Intent
import android.content.res.AssetManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import com.assassin.running.money.MainApp
import com.assassin.running.money.R
import com.assassin.running.money.db.entity.ExpectWealth
import com.assassin.running.money.members.ma.MoneyAllocActivity
import com.assassin.running.money.utils.RecyclerViewUtil
import com.assassin.running.money.widget.StatusBarHelper
import kotlinx.android.synthetic.main.home_activity.*
import kotlinx.android.synthetic.main.home_app_bar.*


/**
 * HomeActivity
 * Created by Qulit on 2017/11/27.
 */
class HomeActivity : AppCompatActivity(), IHomeView, NavigationView.OnNavigationItemSelectedListener {

    private val mHomePresenter = HomePresenter(this)
    private var mHomeExpectWealthAdapter: HomeExpectWealthAdapter? = null

    companion object {
        const val TAG = "HomeActivity"
    }

    fun debug(log: Any) = Log.d("qulei", "[${HomeActivity.TAG}]->$log")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        setSupportActionBar(toolbar_home)
        val default = resources.getDimensionPixelSize(R.dimen.status_bar_default_height)
        StatusBarHelper.setStatusBarTransparent(window)
        StatusBarHelper.setStatusBarMarginTop(toolbar = toolbar_home, default = default)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val toggle = ActionBarDrawerToggle(this, drawer_layout_home, toolbar_home,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout_home.addDrawerListener(toggle)
        toggle.syncState()
        navigation_view_home.setNavigationItemSelectedListener(this)

        initRecyclerView()
        mHomePresenter.loadExpectWealth()

        fab.setOnClickListener({
            debug("height = ${toolbar_home.height}")
            Thread {
                kotlin.run {
                    val o = ExpectWealth()
                    o.title = "aaa"
                    o.expectMoney = 10000
                    o.expectIncrease = 600
                    MainApp.getInstance().dbHelper.wealthCategoryDao().add(o, o)
                    val result = MainApp.getInstance().dbHelper.wealthCategoryDao().all
                    for (item in result) {
                        debug("item:${item.id},${item.title},${item.expectIncrease},${item.expectMoney}")
                    }
                }
            }.start()
        })
    }

    override fun onBackPressed() {
        if (!closeDrawer())
            super.onBackPressed()
    }

    override fun getHomeExpectWealthAdapter(): HomeExpectWealthAdapter? {
        return mHomeExpectWealthAdapter
    }

    override fun getAssetManager(): AssetManager {
        return assets
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_menu_money_alloc -> {
                startActivity(Intent(applicationContext, MoneyAllocActivity::class.java))
            }
            R.id.nav_menu_financial_planning -> {

            }
        }
        closeDrawer()
        return true
    }

    private fun closeDrawer(): Boolean =
            if (drawer_layout_home.isDrawerOpen(GravityCompat.START)) {
                drawer_layout_home.closeDrawer(GravityCompat.START)
                true
            } else {
                false
            }

    private fun initRecyclerView() {
        val lm = LinearLayoutManager(applicationContext)
        lm.orientation = LinearLayoutManager.VERTICAL
        mHomeExpectWealthAdapter = HomeExpectWealthAdapter(applicationContext)
        recycler_home.layoutManager = lm
        recycler_home.adapter = mHomeExpectWealthAdapter
        recycler_home.addItemDecoration(RecyclerViewUtil.getCommonVerticalDivider(applicationContext))
    }

}