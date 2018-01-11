package com.assassin.running.money.home

import android.util.Log
import com.assassin.running.money.dto.ExpectWealthDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.io.InputStreamReader

/**
 * HomePresenter
 * Created by Qulit on 2018/1/10.
 */
class HomePresenter(val view: IHomeView) : IHomePresenter {

    companion object {
        val TAG = "HomePresenter"
    }

    fun debug(log: Any) = Log.d("qulei", "[$TAG]->$log")

    override fun loadExpectWealth() {
        debug("Thread1 = ${Thread.currentThread()}")
        launch {
            debug("Thread 000 = ${Thread.currentThread()}")
            val iStream = view.getAssetManager().open("expect_wealth.list.json")
            val reader = JsonReader(InputStreamReader(iStream, "UTF-8"))
            val list: List<ExpectWealthDto> = Gson().fromJson(reader,
                    object : TypeToken<ArrayList<ExpectWealthDto>>() {}.type)
            iStream.close()
            launch(UI) {
                debug("Thread 999 = ${Thread.currentThread()}")
                view.getHomeExpectWealthAdapter()?.addAll(list)
            }
        }
    }

}