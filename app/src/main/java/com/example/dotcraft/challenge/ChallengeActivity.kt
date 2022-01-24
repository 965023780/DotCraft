package com.example.dotcraft.challenge

import com.example.dotcraft.Constants

import com.example.dotcraft.base.BaseCraftActivity
import com.example.dotcraft.challenge.strategy.ChallengeStrategy
import com.example.dotcraft.util.SharedPreferencesUtil

class ChallengeActivity : BaseCraftActivity() {
    var strategy: ChallengeStrategy? = null

    override fun initView() {
        super.initView()
        strategy = intent.getSerializableExtra(Constants.Opt.CHALLENGE) as ChallengeStrategy
        reset()
    }

    override fun reset() {
        strategy?.let {
            craftView!!.init(it.getRows(), it.getCols(), it.getWhiteDotNumbers())
        }
    }

    override fun saveSuccessInf() {
        SharedPreferencesUtil.instance.saveRank(strategy!!.getChallengeTag(), mTime)
    }

    override fun saveContinueInf() {

    }
}