package com.assassin.running.money.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Le-q on 2018/1/4.
 */
@Entity(tableName = "expect_wealth")
class ExpectWealth {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ew_id")
    var id: Int = 0

    @ColumnInfo(name = "ew_title")
    var title: String = ""

    @ColumnInfo(name = "ew_money")
    var expectMoney: Long = 0   //预计投资金额

    @ColumnInfo(name = "ew_increase")
    var expectIncrease: Int = 0 //预计收益率
}