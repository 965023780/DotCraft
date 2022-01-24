package com.example.dotcraft.challenge

import com.example.dotcraft.App

import com.example.dotcraft.base.BaseCraftActivity

class ChallengeActivity : BaseCraftActivity() {


    override fun initView() {
        super.initView()
        val strategy = App.getContext()!!.mChallengeStrategy
        if (strategy != null) {
            craftView!!.init(strategy.getRows(), strategy.getCols(), strategy.getWhiteDotNumbers())
        }
    }

}