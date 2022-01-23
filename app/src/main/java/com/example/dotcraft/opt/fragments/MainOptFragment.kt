package com.example.dotcraft.opt.fragments

import android.content.Intent
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.example.dotcraft.Constants
import com.example.dotcraft.R
import com.example.dotcraft.base.BaseFragment
import com.example.dotcraft.custom.CustomActivity
import com.example.dotcraft.rank.RankActivity

class MainOptFragment(listener: FragmentListener) : BaseFragment(listener), View.OnClickListener {

    override fun getLayoutResID(): Int = R.layout.fragment_main_opt

    override fun init() {
        mView!!.findViewById<AppCompatButton>(R.id.btn_level).setOnClickListener(this)
        mView!!.findViewById<AppCompatButton>(R.id.btn_challenge).setOnClickListener(this)
        mView!!.findViewById<AppCompatButton>(R.id.btn_custom).setOnClickListener(this)
        mView!!.findViewById<AppCompatButton>(R.id.btn_rank).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_level -> {
                mListener?.changeFragment(Constants.Opt.OPT_STATUS_LEVEL)
            }
            R.id.btn_challenge -> {
                mListener?.changeFragment(Constants.Opt.OPT_STATUS_CHALLENGE)
            }
            R.id.btn_custom -> {
                val intent = Intent(activity, CustomActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_rank -> {
                val intent = Intent(activity, RankActivity::class.java)
                startActivity(intent)
            }
        }
    }
}