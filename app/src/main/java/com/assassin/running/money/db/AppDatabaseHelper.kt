package com.assassin.running.money.db

import android.arch.persistence.room.Room
import android.content.Context

/**
 * AppDatabaseHelper
 * Created by Qulit on 2018/1/4.
 */
class AppDatabaseHelper constructor(context: Context) {
    val database = Room.databaseBuilder(context,
            AppDatabase::class.java, "running-money-database").build()

}