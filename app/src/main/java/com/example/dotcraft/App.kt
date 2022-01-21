package com.example.dotcraft

import android.app.Application

class App : Application() {

    companion object {

        private var mContext: App? = null

        fun getContext(): App? {
            return mContext
        }
    }

    var mLevel: Level? = null

    override fun onCreate() {
        super.onCreate()
        mContext = this
    }

    enum class Level {
        SIMPLE,
        MEDIUM,
        DIFFICULT
    }

}