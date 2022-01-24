package com.example.dotcraft.level

import com.example.dotcraft.Constants
import com.example.dotcraft.base.BaseCraftActivity
import com.example.dotcraft.level.bean.LevelBean
import com.example.dotcraft.util.SharedPreferencesUtil

class LevelActivity : BaseCraftActivity() {
    private var level: LevelBean? = null
    override fun initView() {
        super.initView()
        level = intent.getSerializableExtra(Constants.Opt.LEVEL) as LevelBean
        reset()
    }

    override fun reset() {
        level?.let {
            craftView!!.init(it.strategy.getTypeList(), it.strategy.getCurTypeList())
        }
    }

    override fun saveSuccessInf() {
        SharedPreferencesUtil.instance.saveLevelPassed(level!!.id)
        SharedPreferencesUtil.instance.saveRank(Constants.Opt.LEVEL + level!!.id, mTime)
    }


    override fun saveContinueInf() {

    }

}