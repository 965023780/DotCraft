package com.example.dotcraft.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Paint

class DotView(context: Context) : CircleView(context) {

    var mType = DotType.WHITE

    override fun setPaint() {
        mPaint.color = mCircleColor
        mPaint.style = Paint.Style.FILL
    }

    fun setCircleColor(type: DotType) {
        mType = type
        mCircleColor = when (type) {
            DotType.BLACK -> {
                Color.BLACK
            }
            DotType.WHITE -> {
                Color.WHITE
            }
        }
    }

    enum class DotType {
        WHITE,
        BLACK
    }

}
