package com.example.dotcraft.opt.fragments

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dotcraft.R
import com.example.dotcraft.base.BaseFragment
import com.example.dotcraft.opt.adapter.LevelOptAdapter

class LevelOptFragment(listener: FragmentListener) : BaseFragment(listener) {

    override fun getLayoutResID(): Int = R.layout.fragment_level_opt

    override fun init() {
        val adapter = context?.let { LevelOptAdapter(it) }
        val recyclerView = mView!!.findViewById<RecyclerView>(R.id.rv_level)
        adapter?.let { recyclerView.adapter = adapter }
        recyclerView.layoutManager = GridLayoutManager(context,3)
    }

}