package com.example.dotcraft.opt

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.Fragment
import com.example.dotcraft.Constants
import com.example.dotcraft.R
import com.example.dotcraft.base.BaseActivity
import com.example.dotcraft.base.BaseFragment
import com.example.dotcraft.opt.fragments.ChallengeOptFragment
import com.example.dotcraft.opt.fragments.LevelOptFragment
import com.example.dotcraft.opt.fragments.MainOptFragment

class OptActivity : BaseActivity(), View.OnClickListener, BaseFragment.FragmentListener {

    private val fragmentMap =
        mapOf<String, Fragment>(
            Constants.Opt.OPT_STATUS_MAIN to MainOptFragment(this),
            Constants.Opt.OPT_STATUS_CHALLENGE to ChallengeOptFragment(this),
            Constants.Opt.OPT_STATUS_LEVEL to LevelOptFragment(this)
        )

    private var mStatus = Constants.Opt.OPT_STATUS_MAIN

    override fun getLayoutResID(): Int = R.layout.activity_opt

    override fun init() {
        initView()
    }

    private fun initView() {
        val back = findViewById<AppCompatImageView>(R.id.iv_back)
        back.visibility = View.INVISIBLE
        back.setOnClickListener(this)
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        fragmentMap.forEach { (key, value) ->
            transaction.add(R.id.container_opt, value, key)
            transaction.hide(value)
        }
        transaction.show(fragmentMap[Constants.Opt.OPT_STATUS_MAIN]!!)
        transaction.commit()
    }

    override fun changeFragment(targetTag: String) {
        if (fragmentMap.containsKey(targetTag)) {
            val manager = supportFragmentManager
            val transaction = manager.beginTransaction()
            transaction.hide(fragmentMap[mStatus]!!)
            transaction.show(fragmentMap[targetTag]!!)
            transaction.commit()
        }
        when (targetTag) {
            Constants.Opt.OPT_STATUS_MAIN -> {
                findViewById<AppCompatImageView>(R.id.iv_back).visibility = View.INVISIBLE
            }
            else -> {
                findViewById<AppCompatImageView>(R.id.iv_back).visibility = View.VISIBLE
            }
        }
        mStatus = targetTag
    }

    override fun onClick(v: View?) {
        changeFragment(Constants.Opt.OPT_STATUS_MAIN)
    }

}