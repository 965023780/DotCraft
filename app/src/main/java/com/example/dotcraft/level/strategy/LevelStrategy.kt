package com.example.dotcraft.level.strategy

import com.example.dotcraft.widget.DotView

interface LevelStrategy {
    fun getTypeList(): ArrayList<Array<DotView.DotType>>
    fun getCurTypeList(): ArrayList<Array<DotView.DotType>>
}