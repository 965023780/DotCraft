package com.example.dotcraft

import android.app.Application
import com.example.dotcraft.challenge.strategy.ChallengeStrategy
import com.example.dotcraft.util.SharedPreferencesUtil

class App : Application() {

    companion object {

        private var mContext: App? = null

        fun getContext(): App? {
            return mContext
        }
    }

    var mLastLevel: Int? = null

    override fun onCreate() {
        super.onCreate()
        mContext = this
        val instance = SharedPreferencesUtil.instance
        instance.init(applicationContext)
        getContinueInf(instance)
    }

    private fun getContinueInf(instance: SharedPreferencesUtil) {

    }


}