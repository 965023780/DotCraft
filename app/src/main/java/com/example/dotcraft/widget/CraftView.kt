package com.example.dotcraft.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup
import kotlin.collections.ArrayList
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class CraftView : ViewGroup {

    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet) : super(context, attr)

    private var mCol = 0
    private var mRow = 0
    private var mWhiteDotNumber = 0

    private var mStartX = 0f
    private var mStartY = 0f
    private var mDeltaX = 0f
    private var mDeltaY = 0f
    private var mTargetIndex = -1

    var mStatus = CraftStatus.IDLE
    var mListener: CirqueView.OnViewListener? = null

    private val CHILD_OFFSET = dp2px(10)
    private val mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
    private val mTypeList by lazy {
        ArrayList<Array<DotView.DotType>>()
    }

    private val mCurTypeList by lazy {
        ArrayList<Array<DotView.DotType>>()
    }

    private val mBackupDot by lazy {
        DotView(context)
    }

    private val mDotLayoutParams by lazy {
        LayoutParams(0, 0)
    }
    private val mCirqueLayoutParams by lazy {
        LayoutParams(0, 0)
    }


    fun init(
        typeList: ArrayList<Array<DotView.DotType>>, curTypeList: ArrayList<Array<DotView.DotType>>
    ) {
        if (typeList.isEmpty() || typeList[0].isEmpty()) {
            return
        }
        initParams(typeList)
        addDotView()
        for (i in 0 until mRow) {
            for (j in 0 until mCol) {
                mTypeList[i][j] = typeList[i][j]
                mCurTypeList[i][j] = curTypeList[i][j]
                if (typeList[i][j] == DotView.DotType.WHITE) {
                    val childView = CirqueView(context)
                    childView.layoutParams = mCirqueLayoutParams
                    addView(childView)
                    mWhiteDotNumber++
                }
            }
        }
        (getChildAt(mRow * mCol + 1) as CirqueView).mListener = mListener
        postInvalidate()
    }

    /*
        随机初始化
     */
    fun init(row: Int, col: Int, whiteDotNumber: Int) {
        if (row * col < whiteDotNumber) {
            return
        }
        initParams(row, col, whiteDotNumber)
        addDotView()
        for (i in 0 until whiteDotNumber) {
            while (true) {
                val randRow = (0 until row).random()
                val randCol = (0 until col).random()
                if (mTypeList[randRow][randCol] == DotView.DotType.BLACK) {
                    mTypeList[randRow][randCol] = DotView.DotType.WHITE
                    val childView = CirqueView(context)
                    childView.layoutParams = mCirqueLayoutParams
                    addView(childView)
                    break
                }
            }
            while (true) {
                val randRow = (0 until row).random()
                val randCol = (0 until col).random()
                if (mCurTypeList[randRow][randCol] == DotView.DotType.BLACK) {
                    mCurTypeList[randRow][randCol] = DotView.DotType.WHITE
                    break
                }
            }
        }
        (getChildAt(mRow * mCol + 1) as CirqueView).mListener = mListener
        postInvalidate()
    }


    private fun initParams(row: Int, col: Int, whiteDotNumber: Int) {
        removeAllViews()
        mTypeList.clear()
        mCurTypeList.clear()
        mRow = row
        mCol = col
        mWhiteDotNumber = whiteDotNumber
        mBackupDot.layoutParams = mDotLayoutParams
        mBackupDot.visibility = View.INVISIBLE
        mStatus = CraftStatus.IDLE
        for (i in 0 until row) {
            val array = Array(col) { DotView.DotType.BLACK }
            val curArray = Array(col) { DotView.DotType.BLACK }
            mTypeList.add(array)
            mCurTypeList.add(curArray)
        }
    }

    private fun initParams(typeList: ArrayList<Array<DotView.DotType>>) {
        removeAllViews()
        mTypeList.clear()
        mCurTypeList.clear()
        mBackupDot.layoutParams = mDotLayoutParams
        mBackupDot.visibility = View.INVISIBLE
        mStatus = CraftStatus.IDLE
        mWhiteDotNumber = 0
        mRow = typeList.size
        mCol = typeList[0].size
        for (i in 0 until mRow) {
            val array = Array(mCol) { DotView.DotType.BLACK }
            val curArray = Array(mCol) { DotView.DotType.BLACK }
            mTypeList.add(array)
            mCurTypeList.add(curArray)
        }
    }

    private fun addDotView() {
        val tot = mRow * mCol
        for (i in 0 until tot) {
            val childView = DotView(context)
            childView.layoutParams = mDotLayoutParams
            addView(childView)
        }
        addView(mBackupDot)
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (mCol != 0 && mRow != 0) {
            mDotLayoutParams.width = (measuredWidth - CHILD_OFFSET * (mCol + 1)) / mCol
            mDotLayoutParams.height = (measuredHeight - CHILD_OFFSET * (mRow + 1)) / mRow
            mCirqueLayoutParams.width = mDotLayoutParams.width + CHILD_OFFSET
            mCirqueLayoutParams.height = mDotLayoutParams.height + CHILD_OFFSET
            measureChildren(widthMeasureSpec, heightMeasureSpec)
        }
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var curPosX = CHILD_OFFSET
        var curPosY = CHILD_OFFSET
        var cirqueIndex = mRow * mCol + 1 //The first index of CirqueIndex

        //backupDot layout
        mBackupDot.layout(
            CHILD_OFFSET,
            CHILD_OFFSET,
            CHILD_OFFSET + mBackupDot.measuredWidth,
            CHILD_OFFSET + mBackupDot.measuredHeight
        )

        //DotView and CirqueView layout
        for (i in 0 until mRow) {
            for (j in 0 until mCol) {
                val childView = getChildAt(i * mCol + j) as DotView
                childView.layout(
                    curPosX,
                    curPosY,
                    curPosX + childView.measuredWidth,
                    curPosY + childView.measuredHeight
                )

                if (mTypeList[i][j] == DotView.DotType.WHITE) {
                    val cirqueView = getChildAt(cirqueIndex)
                    cirqueView.layout(
                        curPosX - CHILD_OFFSET,
                        curPosY - CHILD_OFFSET,
                        curPosX + cirqueView.measuredWidth,
                        curPosY + cirqueView.measuredHeight
                    )
                    cirqueIndex++
                }

                if (j + 1 == mCol) {
                    curPosX = CHILD_OFFSET
                    curPosY += childView.height + CHILD_OFFSET
                } else {
                    curPosX += childView.width + CHILD_OFFSET
                }
            }
        }
    }

    override fun dispatchDraw(canvas: Canvas?) {
        canvas?.clipRect(CHILD_OFFSET, CHILD_OFFSET, width - CHILD_OFFSET, height - CHILD_OFFSET)
        if (mStatus == CraftStatus.IDLE) {
            for (i in 0 until mRow) {
                for (j in 0 until mCol) {
                    val childView = getChildAt(i * mCol + j) as DotView
                    when (mCurTypeList[i][j]) {
                        DotView.DotType.BLACK -> {
                            childView.setCircleColor(DotView.DotType.BLACK)
                        }
                        DotView.DotType.WHITE -> {
                            childView.setCircleColor(DotView.DotType.WHITE)
                        }
                    }
                }
            }
        }
        super.dispatchDraw(canvas)
    }


    private fun checkSuccess() {
        for (i in 0 until mRow) {
            for (j in 0 until mCol) {
                if (mCurTypeList[i][j] != mTypeList[i][j]) {
                    return
                }
            }
        }
        startSuccessAnim()
    }

    private fun startSuccessAnim() {
        for (i in mRow * mCol + 1 until childCount) {
            (getChildAt(i) as CirqueView).startAnim()
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (mStatus == CraftStatus.IDLE) {
            return super.onTouchEvent(event)
        }
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                mStartX = event.x
                mStartY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                mDeltaX = event.x - mStartX
                mDeltaY = event.y - mStartY
                when (mStatus) {
                    CraftStatus.WAIT -> {
                        if (abs(mDeltaX) >= mTouchSlop || abs(mDeltaY) >= mTouchSlop) {
                            mStatus =
                                if (abs(mDeltaX) > abs(mDeltaY)) CraftStatus.HORIZONTAL_MOVING
                                else CraftStatus.VERTICAL_MOVING
                            mTargetIndex = checkIndex()
                        }
                    }
                    CraftStatus.HORIZONTAL_MOVING -> {
                        horizontalMoving()
                    }
                    CraftStatus.VERTICAL_MOVING -> {
                        verticalMoving()
                    }
                    else -> {}
                }
            }
            MotionEvent.ACTION_UP -> {
                when (mStatus) {
                    CraftStatus.HORIZONTAL_MOVING -> {
                        horizontalMoveEnd()
                    }
                    CraftStatus.VERTICAL_MOVING -> {
                        verticalMoveEnd()
                    }
                    else -> {}
                }
            }
        }

        return true
    }


    //返回触摸位置是第几行或第几列
    private fun checkIndex(): Int =
        when (mStatus) {
            CraftStatus.VERTICAL_MOVING -> {
                if (mStartX < CHILD_OFFSET / 2) {
                    0
                } else {
                    ((mStartX - CHILD_OFFSET / 2) / (mBackupDot.width + CHILD_OFFSET)).toInt()
                }
            }
            else -> {
                if (mStartY < CHILD_OFFSET / 2) {
                    0
                } else {
                    ((mStartY - CHILD_OFFSET / 2) / (mBackupDot.height + CHILD_OFFSET)).toInt()
                }
            }
        }

    private fun changeDotColor(dotView: DotView, type: DotView.DotType) {
        if (type != dotView.mType) {
            dotView.setCircleColor(type)
            dotView.invalidate()
        }
    }

    private fun horizontalMoving() {
        val translationX = getValidTranslation()
        for (i in mCol * mTargetIndex until mCol * (mTargetIndex + 1)) {
            getChildAt(i).translationX = translationX
        }
        if (mBackupDot.visibility != View.VISIBLE) {
            mBackupDot.visibility = View.VISIBLE
        }
        if (translationX > 0) {
            mBackupDot.translationX =
                translationX - mBackupDot.width - CHILD_OFFSET
            changeDotColor(
                mBackupDot,
                (getChildAt(mCol * mTargetIndex + mCol - 1) as DotView).mType
            )
        } else {
            mBackupDot.translationX = translationX + (mBackupDot.width + CHILD_OFFSET) * mCol
            changeDotColor(mBackupDot, (getChildAt(mCol * mTargetIndex) as DotView).mType)
        }
        mBackupDot.translationY = ((mBackupDot.height + CHILD_OFFSET) * mTargetIndex).toFloat()
        invalidate()
    }

    private fun verticalMoving() {
        val translationY = getValidTranslation()
        for (i in mTargetIndex until mRow * mCol step mCol) {
            getChildAt(i).translationY = translationY
        }
        if (mBackupDot.visibility != View.VISIBLE) {
            mBackupDot.visibility = View.VISIBLE
        }
        if (translationY > 0) {
            mBackupDot.translationY =
                translationY - mBackupDot.height - CHILD_OFFSET
            changeDotColor(
                mBackupDot,
                (getChildAt(mTargetIndex + (mRow - 1) * mCol) as DotView).mType
            )
        } else {
            mBackupDot.translationY = translationY + (mBackupDot.height + CHILD_OFFSET) * mRow
            changeDotColor(mBackupDot, (getChildAt(mTargetIndex) as DotView).mType)
        }
        mBackupDot.translationX = ((mBackupDot.width + CHILD_OFFSET) * mTargetIndex).toFloat()
        invalidate()

    }

    private fun horizontalMoveEnd() {
        mBackupDot.visibility = View.INVISIBLE
        mBackupDot.translationX = 0f
        mBackupDot.translationY = 0f
        val translationX = getValidTranslation()
        for (i in 0 until mCol) {
            getChildAt(i + mCol * mTargetIndex).translationX = 0f
        }
        if (translationX > (mBackupDot.width + CHILD_OFFSET) / 2) {
            val tmp = mCurTypeList[mTargetIndex][mCol - 1]
            for (i in mCol - 1 downTo 1) {
                changeDotColor(
                    getChildAt(i + mCol * mTargetIndex) as DotView,
                    mCurTypeList[mTargetIndex][i - 1]
                )
                mCurTypeList[mTargetIndex][i] = mCurTypeList[mTargetIndex][i - 1]
            }
            changeDotColor(
                getChildAt(mCol * mTargetIndex) as DotView,
                tmp
            )
            mCurTypeList[mTargetIndex][0] = tmp
        } else if (translationX < -(mBackupDot.width + CHILD_OFFSET) / 2) {
            val tmp = mCurTypeList[mTargetIndex][0]
            for (i in 0 until mCol - 1) {
                changeDotColor(
                    getChildAt(i + mCol * mTargetIndex) as DotView,
                    mCurTypeList[mTargetIndex][i + 1]
                )
                mCurTypeList[mTargetIndex][i] = mCurTypeList[mTargetIndex][i + 1]
            }
            changeDotColor(
                getChildAt(mCol - 1 + mCol * mTargetIndex) as DotView,
                tmp
            )
            mCurTypeList[mTargetIndex][mCol - 1] = tmp
        }
        mStatus = CraftStatus.WAIT
        checkSuccess()
        invalidate()
    }

    private fun verticalMoveEnd() {
        mBackupDot.visibility = View.INVISIBLE
        mBackupDot.translationX = 0f
        mBackupDot.translationY = 0f
        val translationY = getValidTranslation()

        for (i in 0 until mRow) {
            getChildAt(i * mCol + mTargetIndex).translationY = 0f
        }
        if (translationY > (mBackupDot.height + CHILD_OFFSET) / 2) {
            val tmp = mCurTypeList[mRow - 1][mTargetIndex]
            for (i in mRow - 1 downTo 1) {
                changeDotColor(
                    getChildAt(i * mCol + mTargetIndex) as DotView,
                    mCurTypeList[i - 1][mTargetIndex]
                )
                mCurTypeList[i][mTargetIndex] = mCurTypeList[i - 1][mTargetIndex]
            }
            changeDotColor(
                getChildAt(mTargetIndex) as DotView,
                tmp
            )
            mCurTypeList[0][mTargetIndex] = tmp
        } else if (translationY < -(mBackupDot.height + CHILD_OFFSET) / 2) {
            val tmp = mCurTypeList[0][mTargetIndex]
            for (i in 0 until mRow - 1) {
                changeDotColor(
                    getChildAt(i * mCol + mTargetIndex) as DotView,
                    mCurTypeList[i + 1][mTargetIndex]
                )
                mCurTypeList[i][mTargetIndex] = mCurTypeList[i + 1][mTargetIndex]
            }
            changeDotColor(
                getChildAt((mRow - 1) * mCol + mTargetIndex) as DotView,
                tmp
            )
            mCurTypeList[mRow - 1][mTargetIndex] = tmp
        }
        mStatus = CraftStatus.WAIT
        checkSuccess()
        invalidate()
    }


    private fun getValidTranslation(): Float =
        when (mStatus) {
            CraftStatus.HORIZONTAL_MOVING -> {
                min(
                    (mBackupDot.width + CHILD_OFFSET) * 1f,
                    max(mDeltaX, (mBackupDot.width + CHILD_OFFSET) * -1f)
                )
            }
            CraftStatus.VERTICAL_MOVING -> {
                min(
                    (mBackupDot.height + CHILD_OFFSET) * 1f,
                    max(mDeltaY, (mBackupDot.height + CHILD_OFFSET) * -1f)
                )
            }
            else -> {
                0f
            }
        }

    private fun dp2px(value: Int): Int =
        (context.resources.displayMetrics.density * value + 0.5f).toInt()


    enum class CraftStatus {
        IDLE,
        WAIT,
        HORIZONTAL_MOVING,
        VERTICAL_MOVING,
    }

}