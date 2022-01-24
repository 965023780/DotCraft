package com.example.dotcraft.level.strategy

import com.example.dotcraft.widget.DotView

open class BaseLevel:LevelStrategy {
    val mTypeList by lazy {
        ArrayList<Array<DotView.DotType>>()
    }

    val mCurTypeList by lazy {
        ArrayList<Array<DotView.DotType>>()
    }

    override fun getTypeList(): ArrayList<Array<DotView.DotType>> = mTypeList

    override fun getCurTypeList(): ArrayList<Array<DotView.DotType>> = mCurTypeList

}