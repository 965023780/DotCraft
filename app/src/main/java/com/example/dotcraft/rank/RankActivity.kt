package com.example.dotcraft.rank

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dotcraft.R
import com.example.dotcraft.base.BaseActivity
import com.example.dotcraft.opt.OptActivity
import com.example.dotcraft.rank.adapter.RankAdapter

class RankActivity : BaseActivity(), View.OnClickListener {
    override fun getLayoutResID(): Int = R.layout.activity_rank

    override fun init() {
        val adapter = RankAdapter(this)
        val recyclerView = findViewById<RecyclerView>(R.id.rv_rank)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.iv_back -> {
                val intent = Intent(this, OptActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

}