package com.example.dotcraft.challenge

import android.view.View
import com.example.dotcraft.App

import com.example.dotcraft.base.BaseCraftActivity

class ChallengeActivity : BaseCraftActivity(), View.OnClickListener {


    override fun initView() {
        super.initView()
        val strategy = App.getContext()!!.mChallengeStrategy
        if (strategy != null) {
            craftView!!.init(strategy.getRows(), strategy.getCols(), strategy.getWhiteDotNumbers())
        }
    }

}