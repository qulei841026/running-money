package com.assassin.running.money

import android.app.Application
import android.content.Context
import android.util.Log
import com.assassin.running.money.db.AppDatabaseHelper

class MainApp : Application() {

    companion object {
        private var app: MainApp? = null

        fun getInstance(): MainApp {
            return app!!
        }
    }

    /**
     * 手机屏幕参数
     */
    private var density: Float = 0f
    private var densityDpi: Int = 0
    private var screenWidth: Int = 0
    private var screenHeight: Int = 0

    val dbHelper = AppDatabaseHelper(this).database

    override fun onCreate() {
        super.onCreate()
        app = MainApp@ this
        Log.i("qulei", "MainApp onCreate")
        screenDisplay(this)
    }

    private fun screenDisplay(context: Context) {
        val dm = context.resources.displayMetrics
        screenWidth = dm.widthPixels       //屏幕宽度（像素）
        screenHeight = dm.heightPixels     //屏幕高度（像素）
        density = dm.density               //屏幕密度（0.75 / 1.0 / 1.5）
        densityDpi = dm.densityDpi         //屏幕密度DPI（120 / 160 / 240）

        Log.d("qulei", " screenDisplay - screenWidth :" + screenWidth
                + ",screenHeight :" + screenHeight
                + ",density :" + density + ",densityDpi :" + densityDpi)
    }


}