package com.example.dotcraft.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class DotView : View {

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    var mType = DotType.WHITE

    private val mPaint by lazy {
        Paint()
    }

    private var mCircleColor = Color.WHITE

    private fun setPaint() {
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

    override fun onDraw(canvas: Canvas?) {
        val cx = width / 2f
        val cy = height / 2f
        val radius = min(cx, cy) / 2
        setPaint()
        canvas!!.drawCircle(cx, cy, radius, mPaint)
        super.onDraw(canvas)
    }


    enum class DotType {
        WHITE,
        BLACK
    }

}
