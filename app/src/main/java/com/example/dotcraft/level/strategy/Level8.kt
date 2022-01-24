package com.example.dotcraft.level.strategy

import com.example.dotcraft.widget.DotView

class Level8 : BaseLevel() {

    init {
        for (i in 0 until 5) {
            val array = Array(5) { DotView.DotType.BLACK }
            val curArray = Array(5) { DotView.DotType.BLACK }
            mTypeList.add(array)
            mCurTypeList.add(curArray)
        }
        mTypeList[0][0] = DotView.DotType.WHITE
        mTypeList[0][2] = DotView.DotType.WHITE
        mTypeList[1][0] = DotView.DotType.WHITE
        mTypeList[1][4] = DotView.DotType.WHITE
        mTypeList[2][1] = DotView.DotType.WHITE
        mTypeList[2][4] = DotView.DotType.WHITE
        mTypeList[3][3] = DotView.DotType.WHITE
        mTypeList[3][4] = DotView.DotType.WHITE
        mTypeList[4][0] = DotView.DotType.WHITE
        mTypeList[4][2] = DotView.DotType.WHITE
        mTypeList[4][3] = DotView.DotType.WHITE
        mTypeList[4][4] = DotView.DotType.WHITE

        mCurTypeList[0][0] = DotView.DotType.WHITE
        mCurTypeList[0][1] = DotView.DotType.WHITE
        mCurTypeList[1][2] = DotView.DotType.WHITE
        mCurTypeList[1][4] = DotView.DotType.WHITE
        mCurTypeList[2][1] = DotView.DotType.WHITE
        mCurTypeList[2][3] = DotView.DotType.WHITE
        mCurTypeList[2][4] = DotView.DotType.WHITE
        mCurTypeList[3][1] = DotView.DotType.WHITE
        mCurTypeList[3][2] = DotView.DotType.WHITE
        mCurTypeList[3][4] = DotView.DotType.WHITE
        mCurTypeList[4][2] = DotView.DotType.WHITE
        mCurTypeList[4][4] = DotView.DotType.WHITE
    }

}