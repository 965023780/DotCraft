package com.example.dotcraft.widget

import android.content.Context
import android.graphics.Paint

class CirqueView(context: Context) : CircleView(context) {


    override fun setPaint() {
        mPaint.color = mCircleColor
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = dp2px(3).toFloat()
    }

}
