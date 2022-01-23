package com.example.dotcraft.util

import android.content.Context
import android.content.SharedPreferences
import com.example.dotcraft.Constants

class SharedPreferencesUtil private constructor() {

    companion object {
        val instance: SharedPreferencesUtil by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SharedPreferencesUtil()
        }
    }

    private var sp: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    fun init(context: Context) {
        sp = context.getSharedPreferences(
            Constants.Sp.SHARED_PREFERENCE_FILE_NAME,
            Context.MODE_PRIVATE
        )
        editor = sp!!.edit()
    }

    fun getLevelPassed(): Int = getInt(Constants.Sp.LEVEL_PASSED, 0)

    private fun saveInt(key: String, values: Int) {
        editor!!.putInt(key, values).apply()
    }

    private fun getInt(key: String, defaultValue: Int): Int = sp!!.getInt(key, defaultValue)

}