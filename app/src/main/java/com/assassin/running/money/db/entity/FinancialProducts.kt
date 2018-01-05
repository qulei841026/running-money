package com.assassin.running.money.db.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by Le-q on 2018/1/4.
 */
@Entity(tableName = "financial_products")
class FinancialProducts {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fp_id")
    var id: Int = 0

    @ColumnInfo(name = "fp_type")
    var type: Int = 0

    @ColumnInfo(name = "fp_product_name")
    var productName: String = ""

    @ColumnInfo(name = "fp_platform")
    var platform: String = ""

}