package com.assassin.running.money.utils

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem

/**
 * EditTextUtil
 *
 * Created by Qulit on 2018/1/17.
 */
object EditTextUtil {

    fun disableActionCallback(): ActionMode.Callback {
        return object : ActionMode.Callback {
            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return false
            }
        }
    }


}