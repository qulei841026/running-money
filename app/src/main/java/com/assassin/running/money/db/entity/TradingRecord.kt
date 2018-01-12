package com.assassin.running.money.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * 交易记录
 * Created by Qulit on 2018/1/4.
 */
@Entity(tableName = "trading_record")
class TradingRecord {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tr_id")
    var id: Int = 0

    @ColumnInfo(name = "tr_amount_money")
    var amountMoney: Long = 0

    @ColumnInfo(name = "tr_direction")
    var direction: Int = 0

    @ColumnInfo(name = "tr_data")
    var data: Long = 0

}