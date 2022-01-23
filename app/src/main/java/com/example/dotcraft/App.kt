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

    var mLevelPassed = 0
    var mChallengeStrategy: ChallengeStrategy? = null

    override fun onCreate() {
        super.onCreate()
        mContext = this
        SharedPreferencesUtil.instance.init(applicationContext)
        mLevelPassed = SharedPreferencesUtil.instance.getLevelPassed()
    }


}