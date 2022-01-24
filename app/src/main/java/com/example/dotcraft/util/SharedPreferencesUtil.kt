package com.example.dotcraft.util

import android.content.Context
import android.content.SharedPreferences
import com.example.dotcraft.Constants
import com.example.dotcraft.rank.bean.RankBean
import java.util.*
import kotlin.collections.ArrayList

class SharedPreferencesUtil private constructor() {

    companion object {
        val instance: SharedPreferencesUtil by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
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

    fun getLevelPassed(id: Int): Boolean =
        getBoolean(Constants.Sp.LEVEL_PASSED + id)

    fun saveLevelPassed(id: Int) {
        saveBoolean(Constants.Sp.LEVEL_PASSED + id, true)
    }

    fun getRank(): List<RankBean> {
        val rankList = ArrayList<RankBean>()
        val number = getInt(Constants.Rank.INF_NUMBER)
        for (i in 1..number) {
            val bean = RankBean()
            bean.id = i
            bean.type = getString(Constants.Rank.INF_TYPE + i)
            bean.time = getLong(Constants.Rank.INF_TIME + i)
            rankList.add(bean)
        }
        rankList.sort()
        return rankList
    }

    fun saveRank(type: String, time: Long) {
        val number = getInt(Constants.Rank.INF_NUMBER)
        if (number == Constants.Sp.DEFAULT_INT) {
            saveInt(Constants.Rank.INF_NUMBER, 1)
            saveString(Constants.Rank.INF_TYPE + 1, type)
            saveLong(Constants.Rank.INF_TIME + 1, time)
        } else {
            saveInt(Constants.Rank.INF_NUMBER, number + 1)
            saveString(Constants.Rank.INF_TYPE + (number + 1), type)
            saveLong(Constants.Rank.INF_TIME + (number + 1), time)
        }
    }


    private fun saveInt(key: String, values: Int) {
        editor!!.putInt(key, values).apply()
    }


    private fun saveLong(key: String, values: Long) {
        editor!!.putLong(key, values).apply()
    }


    private fun saveBoolean(key: String, values: Boolean) {
        editor!!.putBoolean(key, values).apply()
    }

    private fun saveString(key: String, values: String) {
        editor!!.putString(key, values)
    }

    private fun getBoolean(key: String): Boolean =
        sp!!.getBoolean(key, Constants.Sp.DEFAULT_BOOLEAN)

    private fun getLong(key: String): Long = sp!!.getLong(key, Constants.Sp.DEFAULT_LONG)

    private fun getInt(key: String): Int = sp!!.getInt(key, Constants.Sp.DEFAULT_INT)

    private fun getString(key: String): String = sp!!.getString(key, Constants.Sp.DEFAULT_STRING)!!

}