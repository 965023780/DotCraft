package com.example.dotcraft.level.strategy

import com.example.dotcraft.widget.DotView

class Level5: BaseLevel() {

    init{
        for (i in 0 until 4) {
            val array = Array(4) { DotView.DotType.BLACK }
            val curArray = Array(4) { DotView.DotType.BLACK }
            mTypeList.add(array)
            mCurTypeList.add(curArray)
        }

        mTypeList[0][1] = DotView.DotType.WHITE
        mTypeList[0][3] = DotView.DotType.WHITE
        mTypeList[1][3] = DotView.DotType.WHITE
        mTypeList[2][1] = DotView.DotType.WHITE
        mTypeList[2][2] = DotView.DotType.WHITE
        mTypeList[3][0] = DotView.DotType.WHITE
        mTypeList[3][1] = DotView.DotType.WHITE
        mTypeList[3][2] = DotView.DotType.WHITE

        mCurTypeList[0][0] = DotView.DotType.WHITE
        mCurTypeList[0][1] = DotView.DotType.WHITE
        mCurTypeList[0][2] = DotView.DotType.WHITE
        mCurTypeList[1][0] = DotView.DotType.WHITE
        mCurTypeList[1][3] = DotView.DotType.WHITE
        mCurTypeList[2][1] = DotView.DotType.WHITE
        mCurTypeList[2][2] = DotView.DotType.WHITE
        mCurTypeList[3][2] = DotView.DotType.WHITE

    }

}