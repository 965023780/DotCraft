package com.example.dotcraft

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView

class LevelActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level)
        initView()
    }

    private fun initView() {
        findViewById<AppCompatImageView>(R.id.iv_back).visibility = View.INVISIBLE
        findViewById<AppCompatButton>(R.id.btn_simple).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.btn_medium).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.btn_difficult).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_simple -> {
                App.getContext()?.mLevel = App.Level.SIMPLE
            }
            R.id.btn_medium -> {
                App.getContext()?.mLevel = App.Level.MEDIUM
            }
            R.id.btn_difficult -> {
                App.getContext()?.mLevel = App.Level.DIFFICULT
            }
        }
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

    }
}