package com.assassin.running.money.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.assassin.running.money.db.entity.ExpectWealth

/**
 * Created by Le-q on 2018/1/4.
 */
@Dao
interface ExpectWealthDao {

    @get:Query("SELECT * FROM expect_wealth")
    val all: List<ExpectWealth>

    @Insert
    fun add(vararg expectWealth: ExpectWealth)

}
