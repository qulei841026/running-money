package com.assassin.running.money.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.assassin.running.money.db.dao.ExpectWealthDao
import com.assassin.running.money.db.entity.ExpectWealth

/**
 * Created by Le-q on 2018/1/4.
 */
@Database(entities = [(ExpectWealth::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun wealthCategoryDao(): ExpectWealthDao

}