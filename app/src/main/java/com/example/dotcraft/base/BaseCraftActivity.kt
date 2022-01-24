package com.example.dotcraft.base

import android.annotation.SuppressLint
import android.content.Intent
import android.os.*
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.dotcraft.R
import com.example.dotcraft.opt.OptActivity
import com.example.dotcraft.widget.CirqueView
import com.example.dotcraft.widget.CraftView

abstract class BaseCraftActivity : BaseActivity(), View.OnClickListener, CirqueView.OnViewListener {
    protected var craftView: CraftView? = null
    private var btnStart: AppCompatButton? = null
    private var textView: TextView? = null
    protected var mTime = 0L
    private var mTimeHandler: Handler? = null
    private var mTimeHandlerThread: HandlerThread? = null
    private val MESSAGE_TIME = 1000
    private val mTaskThread = Thread { mUiHandler.sendEmptyMessage(MESSAGE_TIME) }
    private val mUiHandler by lazy {
        UiHandler(Looper.getMainLooper())
    }

    override fun getLayoutResID(): Int = R.layout.activity_craft

    override fun init() {
        initView()
        mTimeHandlerThread = HandlerThread("timeCount")
        mTimeHandlerThread!!.start()
        mTimeHandler = Handler(mTimeHandlerThread!!.looper)
    }

    protected open fun initView() {
        craftView = findViewById(R.id.vp_craft)
        craftView!!.mListener = this
        btnStart = findViewById(R.id.btn_start)
        btnStart!!.setOnClickListener(this)
        textView = findViewById(R.id.tv_time)
        findViewById<AppCompatImageView>(R.id.iv_back).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_start -> {
                when (craftView!!.mStatus) {
                    CraftView.CraftStatus.IDLE -> {
                        startTime()
                        btnStart!!.text = getString(R.string.game_reset)
                        craftView!!.mStatus = CraftView.CraftStatus.WAIT
                    }
                    else -> {
                        craftView!!.mStatus = CraftView.CraftStatus.IDLE
                        reset()
                        stopTime()
                        btnStart!!.text = getString(R.string.game_start)
                    }
                }
            }
            R.id.iv_back -> {
                val intent = Intent(this, OptActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }


    private fun startTime() {
        mTimeHandler!!.post(mTaskThread)
    }

    private fun stopTime() {
        mUiHandler.removeMessages(MESSAGE_TIME)
        mTime = 0
        textView!!.text = getString(R.string.tv_time_default)
    }

    override fun onAnimStartListener() {
        mUiHandler.removeMessages(MESSAGE_TIME)
        saveSuccessInf()
    }

    override fun onAnimEndListener() {
        AlertDialog.Builder(this).setTitle("成功！").setMessage("共耗时$mTime s").create().show()
        mTime = 0
        reset()
        btnStart!!.text = getString(R.string.game_start)
        textView!!.text = getString(R.string.tv_time_default)
    }

    override fun onDestroy() {
        super.onDestroy()
        mUiHandler.removeMessages(MESSAGE_TIME)
        saveContinueInf()
    }

    inner class UiHandler(looper: Looper) : Handler(looper) {

        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MESSAGE_TIME -> {
                    mTime++
                    textView!!.text = "时间:$mTime s"
                    mUiHandler.sendEmptyMessageDelayed(MESSAGE_TIME, 1000)
                }
            }
        }
    }

    abstract fun reset()

    abstract fun saveSuccessInf()

    abstract fun saveContinueInf()
}
