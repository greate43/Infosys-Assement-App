package com.greate43.sk.infosysassement.utilities

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


class Prefs(context: Context) {

    private val prefsFileName = "prefs"

    private val prefs: SharedPreferences = context.getSharedPreferences(prefsFileName, MODE_PRIVATE)



    var allowQuery :Boolean
        get() = prefs.getBoolean("allowQuery", false)
        set(value) = prefs.edit().putBoolean("allowQuery", value).apply()




}