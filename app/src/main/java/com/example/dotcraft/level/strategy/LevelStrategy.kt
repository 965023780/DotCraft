package com.example.dotcraft.level.strategy

import com.example.dotcraft.widget.DotView
import java.io.Serializable

interface LevelStrategy : Serializable {
    fun getTypeList(): ArrayList<Array<DotView.DotType>>
    fun getCurTypeList(): ArrayList<Array<DotView.DotType>>
}