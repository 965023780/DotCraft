package com.example.dotcraft.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

abstract class CircleView : View {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    protected val mPaint by lazy {
        Paint()
    }

    protected var mCircleColor = Color.WHITE

    protected abstract fun setPaint()

    override fun onDraw(canvas: Canvas?) {
        val cx = width / 2f
        val cy = height / 2f
        val radius = min(cx, cy) / 2
        setPaint()
        canvas!!.drawCircle(cx, cy, radius, mPaint)
        super.onDraw(canvas)
    }

    protected fun dp2px(value: Int): Int =
        (context.resources.displayMetrics.density * value + 0.5f).toInt()

}
