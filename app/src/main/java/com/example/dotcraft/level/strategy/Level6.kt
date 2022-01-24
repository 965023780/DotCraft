package com.example.dotcraft.level.strategy

import com.example.dotcraft.widget.DotView

class Level6 : BaseLevel() {

    init {
        for (i in 0 until 4) {
            val array = Array(4) { DotView.DotType.BLACK }
            val curArray = Array(4) { DotView.DotType.BLACK }
            mTypeList.add(array)
            mCurTypeList.add(curArray)
        }

        mTypeList[0][0] = DotView.DotType.WHITE
        mTypeList[1][0] = DotView.DotType.WHITE
        mTypeList[1][1] = DotView.DotType.WHITE
        mTypeList[1][2] = DotView.DotType.WHITE
        mTypeList[2][0] = DotView.DotType.WHITE
        mTypeList[2][1] = DotView.DotType.WHITE
        mTypeList[3][1] = DotView.DotType.WHITE
        mTypeList[3][2] = DotView.DotType.WHITE

        mCurTypeList[0][2] = DotView.DotType.WHITE
        mCurTypeList[1][1] = DotView.DotType.WHITE
        mCurTypeList[1][2] = DotView.DotType.WHITE
        mCurTypeList[1][3] = DotView.DotType.WHITE
        mCurTypeList[2][2] = DotView.DotType.WHITE
        mCurTypeList[2][3] = DotView.DotType.WHITE
        mCurTypeList[3][2] = DotView.DotType.WHITE
        mCurTypeList[3][3] = DotView.DotType.WHITE
    }

}