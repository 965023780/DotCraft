package com.example.dotcraft

import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import com.example.dotcraft.widget.CraftView

class MainActivity : AppCompatActivity(), View.OnClickListener, CraftView.OnCraftViewListener {
    private var craftView: CraftView? = null
    private var btnStart: AppCompatButton? = null
    private var textView: TextView? = null
    private var mTime = 0
    private var mTimeHandler: Handler? = null
    private var mTimeHandlerThread: HandlerThread? = null
    private val MESSAGE_TIME = 1000
    private val mTaskThread = Thread { mUiHandler.sendEmptyMessage(MESSAGE_TIME)}
    private val mUiHandler by lazy {
        UiHandler(Looper.getMainLooper())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        mTimeHandlerThread = HandlerThread("timeCount")
        mTimeHandlerThread!!.start()
        mTimeHandler = Handler(mTimeHandlerThread!!.looper)
    }

    private fun initView() {
        craftView = findViewById(R.id.vp_craft)
        when (App.getContext()?.mLevel) {
            App.Level.SIMPLE -> {
                craftView!!.init(3, 3, 4)
            }
            App.Level.MEDIUM -> {
                craftView!!.init(4, 4, 8)
            }
            App.Level.DIFFICULT -> {
                craftView!!.init(5, 5, 12)
            }
            else -> {

            }
        }
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
                        craftView!!.reset()
                        stopTime()
                        btnStart!!.text = getString(R.string.game_start)
                    }
                }
            }
            R.id.iv_back -> {
                val intent = Intent(this, LevelActivity::class.java)
                startActivity(intent)
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


    override fun onSuccess() {
        AlertDialog.Builder(this).setTitle("成功！").setMessage("共耗时$mTime s").create().show()
        stopTime()
        craftView!!.reset()
        btnStart!!.text = getString(R.string.game_start)
    }

    override fun onDestroy() {
        super.onDestroy()
        mUiHandler.removeMessages(MESSAGE_TIME)
    }

    inner class UiHandler(looper: Looper) : Handler(looper) {

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

}
