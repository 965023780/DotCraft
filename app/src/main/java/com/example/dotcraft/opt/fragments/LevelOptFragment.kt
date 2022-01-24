package com.example.dotcraft.opt.fragments

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dotcraft.Constants
import com.example.dotcraft.R
import com.example.dotcraft.base.BaseFragment
import com.example.dotcraft.level.LevelActivity
import com.example.dotcraft.level.bean.LevelBean
import com.example.dotcraft.opt.adapter.LevelOptAdapter

class LevelOptFragment(listener: FragmentListener) : BaseFragment(listener),
    LevelOptAdapter.OnItemListener {

    override fun getLayoutResID(): Int = R.layout.fragment_level_opt

    override fun init() {
        val adapter = context?.let { LevelOptAdapter(it, this) }
        val recyclerView = mView!!.findViewById<RecyclerView>(R.id.rv_level)
        adapter?.let { recyclerView.adapter = adapter }
        recyclerView.layoutManager = GridLayoutManager(context, 3)
    }

    override fun onClick(level: LevelBean) {
        val intent = Intent(activity, LevelActivity::class.java)
        intent.putExtra(Constants.Opt.LEVEL, level)
        startActivity(intent)
        activity?.finish()
    }

}