package com.example.dotcraft.widget

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class CirqueView : View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    private val mPaint by lazy {
        Paint()
    }

    private val oval by lazy {
        RectF()
    }

    private var currentValue = 0f
    private var playingAnim = false
    private var mCircleColor = Color.WHITE
    var mListener: OnViewListener? = null

    private fun setPaint() {
        mPaint.color = mCircleColor
        mPaint.style = Paint.Style.STROKE
        mPaint.isAntiAlias = true
        mPaint.strokeWidth = dp2px(3).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        val cx = width / 2f
        val cy = height / 2f
        val radius = min(cx, cy) / 2
        setPaint()
        if (!playingAnim) {
            canvas!!.drawCircle(cx, cy, radius, mPaint)
        } else {
            oval.left = radius
            oval.right = oval.left + 2 * radius
            oval.top = radius
            oval.bottom = oval.top + 2 * radius
            canvas!!.drawArc(oval, -90f, currentValue, false, mPaint)
        }
        super.onDraw(canvas)
    }


    fun startAnim() {
        val anim = ValueAnimator.ofFloat(360f, 0f).setDuration(1000)
        anim.addListener(object:Animator.AnimatorListener{
            override fun onAnimationStart(animation: Animator?) {
                mListener?.onAnimStartListener()
            }

            override fun onAnimationEnd(animation: Animator?) {
                mListener?.onAnimEndListener()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationRepeat(animation: Animator?) {
            }

        })
        anim.addUpdateListener { animation ->
            currentValue = animation.animatedValue as Float
            playingAnim = true
            postInvalidate()
        }
        anim.start()
    }


    private fun dp2px(value: Int): Int =
        (context.resources.displayMetrics.density * value + 0.5f).toInt()

    interface OnViewListener {
        fun onAnimStartListener()
        fun onAnimEndListener()
    }

}
