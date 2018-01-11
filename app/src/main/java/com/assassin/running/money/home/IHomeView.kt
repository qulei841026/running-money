package com.assassin.running.money.home

import android.content.res.AssetManager

/**
 * IHomeView
 * Created by Qulit on 2018/1/10.
 */
interface IHomeView {

    fun getHomeExpectWealthAdapter(): HomeExpectWealthAdapter?

    fun getAssetManager(): AssetManager

}