package com.example.dotcraft.level

import com.example.dotcraft.Constants
import com.example.dotcraft.base.BaseCraftActivity
import com.example.dotcraft.level.bean.LevelBean

class LevelActivity : BaseCraftActivity() {
    override fun initView() {
        super.initView()
        val level = intent.getSerializableExtra(Constants.Opt.LEVEL) as LevelBean
        craftView!!.init(level.strategy.getTypeList(), level.strategy.getCurTypeList())
    }
}