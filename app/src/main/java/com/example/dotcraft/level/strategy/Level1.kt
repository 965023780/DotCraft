package com.example.dotcraft.level.strategy

import com.example.dotcraft.widget.DotView

class Level1 : BaseLevel() {

    init {
        for (i in 0 until 3) {
            val array = Array(3) { DotView.DotType.BLACK }
            val curArray = Array(3) { DotView.DotType.BLACK }
            mTypeList.add(array)
            mCurTypeList.add(curArray)
        }
        mTypeList[1][1] = DotView.DotType.WHITE
        mTypeList[1][2] = DotView.DotType.WHITE
        mTypeList[2][0] = DotView.DotType.WHITE
        mTypeList[2][2] = DotView.DotType.WHITE

        mCurTypeList[1][2] = DotView.DotType.WHITE
        mCurTypeList[2][0] = DotView.DotType.WHITE
        mCurTypeList[2][1] = DotView.DotType.WHITE
        mCurTypeList[2][2] = DotView.DotType.WHITE
    }

}